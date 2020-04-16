import dgl
import torch

from dgl.data import MiniGCDataset
import matplotlib.pyplot as plt
import networkx as nx
import torch.nn as nn
import torch.nn.functional as F
from dgl.nn.pytorch import GraphConv
import torch.optim as optim
from torch.utils.data import DataLoader


# 图分类算法，利用消息传递和图形卷积，交换节点间的信息，然后根据信息的交换情况，推测处图的形状
# 数据集有80个样本，每个样本的尺寸为[10, 20]
dataset = MiniGCDataset(80, 10, 20)
graph, label = dataset[0]
fig, ax = plt.subplots()
nx.draw(graph.to_networkx(), ax=ax)
ax.set_title('Class: {:d}'.format(label))
plt.show()


# 将给定的图和标签转换为一个batch
def collate(samples):
    # 输入的样本时一对对的(graph, label)的list
    graphs, labels = map(list, zip(*samples))
    batched_graph = dgl.batch(graphs)
    return batched_graph, torch.tensor(labels)


# 分类器
class Classifier(nn.Module):
    def __init__(self, in_dim, hidden_dim, n_classes):
        super(Classifier, self).__init__()
        self.conv1 = GraphConv(in_dim, hidden_dim)
        self.conv2 = GraphConv(hidden_dim, hidden_dim)
        self.classify = nn.Linear(hidden_dim, n_classes)

    def forward(self, g):
        # 使用节点的度作为节点的初始特征，对于无向图，节点的出度和入度相同
        h = g.in_degrees().view(-1, 1).float()
        # 执行图卷积和激活函数
        h = F.relu(self.conv1(g, h))
        h = F.relu(self.conv2(g, h))
        g.ndata['h'] = h
        # 通过平均所有节点表示来计算图形表示。
        hg = dgl.mean_nodes(g, 'h')
        return self.classify(hg)


# 创建训练集和测试集
trainset = MiniGCDataset(320, 10, 20)
testset = MiniGCDataset(80, 10, 20)
# 使用PyTorch的DataLoader和collate方法
# 定义装载器
data_loader = DataLoader(trainset, batch_size=32, shuffle=True, collate_fn=collate)

# 创建模型
model = Classifier(1, 256, trainset.num_classes)
loss_func = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)
model.train()

epoch_losses = []
for epoch in range(80):
    epoch_loss = 0
    for iter, (bg, label) in enumerate(data_loader):
        prediction = model(bg)
        loss = loss_func(prediction, label)
        optimizer.zero_grad()
        loss.backward()
        optimizer.step()
        epoch_loss += loss.detach().item()
    epoch_loss /= (iter + 1)
    print('Epoch {}, loss {:.4f}'.format(epoch, epoch_loss))
    epoch_losses.append(epoch_loss)