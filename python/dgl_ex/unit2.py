import torch
import itertools

import networkx as nx
import torch.nn.functional as F
import torch.nn as nn

from dgl.nn.pytorch import GraphConv
from unit1 import build_karate_club_graph

import matplotlib.animation as animation
import matplotlib.pyplot as plt


# 创建一个GCN模型并进行训练
class GCN(nn.Module):
    def __init__(self, in_feats, hidden_size, num_classes):
        super(GCN, self).__init__()
        self.conv1 = GraphConv(in_feats, hidden_size)
        self.conv2 = GraphConv(hidden_size, num_classes)

    def forward(self, g, inputs):
        h = self.conv1(g, inputs)
        h = torch.relu(h)
        h = self.conv2(g, h)
        return h

if __name__ == '__main__':
    # 第一层将大小为5的输入要素转换为隐藏大小为5。
    # 第二层转换隐藏层并产生以下输出特征
    # 输出大小2，对应于空手道俱乐部数据集的两组。
    net = GCN(5, 5, 2)

    G = build_karate_club_graph()  # 创建一张图
    embed = nn.Embedding(34, 5)  # 生成节点嵌入层，将34个节点嵌入到5个维度中
    G.ndata['feat'] = embed.weight  # 对图应用嵌入
    inputs = embed.weight

    labeled_nodes = torch.tensor([0, 33])  # 仅标记教师和主席节点(0号和33号)
    labels = torch.tensor([0, 1])  # 这两个节点的标签是不同的
    optimizer = torch.optim.Adam(itertools.chain(net.parameters(), embed.parameters()), lr=0.01)  # 创建优化器
    all_logits = []  # 记录list
    for epoch in range(50):
        logits = net(G, inputs)
        # 保存logit以便以后查看
        all_logits.append(logits.detach())
        logp = F.log_softmax(logits, 1)  # 计算输出
        # 只计算标记节点的损失
        loss = F.nll_loss(logp[labeled_nodes], labels)

        optimizer.zero_grad()  # 重置梯度
        loss.backward()  # 向后传播
        optimizer.step()  # 应用

        print('Epoch %d | Loss: %.4f' % (epoch, loss.item()))


    def draw(i):
        cls1color = '#00FFFF'
        cls2color = '#FF00FF'
        pos = {}
        colors = []
        for v in range(34):
            pos[v] = all_logits[i][v].numpy()
            cls = pos[v].argmax()
            colors.append(cls1color if cls else cls2color)
        ax.cla()
        ax.axis('off')
        ax.set_title('Epoch: %d' % i)
        nx.draw_networkx(nx_G.to_undirected(), pos, node_color=colors,
                         with_labels=True, node_size=300, ax=ax)

    fig = plt.figure(dpi=150)
    fig.clf()
    ax = fig.subplots()
    nx_G = G.to_networkx().to_undirected()
    draw(0)  # 绘制出每轮的预测结果
    plt.close()

    ani = animation.FuncAnimation(fig, draw, frames=len(all_logits), interval=200)