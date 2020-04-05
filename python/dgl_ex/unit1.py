import dgl
import numpy as np
import networkx as nx
import torch.nn as nn


# 构建一张图
def build_karate_club_graph():
    # 78条边都存储在两个numpy数组中。 一个是源端点，另一个是目标端点
    src = np.array([1, 2, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 9, 10, 10,
        10, 11, 12, 12, 13, 13, 13, 13, 16, 16, 17, 17, 19, 19, 21, 21,
        25, 25, 27, 27, 27, 28, 29, 29, 30, 30, 31, 31, 31, 31, 32, 32,
        32, 32, 32, 32, 32, 32, 32, 32, 32, 33, 33, 33, 33, 33, 33, 33,
        33, 33, 33, 33, 33, 33, 33, 33, 33, 33])
    dst = np.array([0, 0, 1, 0, 1, 2, 0, 0, 0, 4, 5, 0, 1, 2, 3, 0, 2, 2, 0, 4,
        5, 0, 0, 3, 0, 1, 2, 3, 5, 6, 0, 1, 0, 1, 0, 1, 23, 24, 2, 23,
        24, 2, 23, 26, 1, 8, 0, 24, 25, 28, 2, 8, 14, 15, 18, 20, 22, 23,
        29, 30, 31, 8, 9, 13, 14, 15, 18, 19, 20, 22, 23, 26, 27, 28, 29, 30,
        31, 32])
    # 在dgl中，边是单向的，建立两次边使边变为双向的
    u = np.concatenate([src, dst])
    v = np.concatenate([dst, src])
    # 构建一个GDL图
    return dgl.DGLGraph((u, v))


if __name__ == '__main__':
    G = build_karate_club_graph()  # 创建一张图
    print('We have %d nodes.' % G.number_of_nodes())
    print('We have %d edges.' % G.number_of_edges())

    # 由于实际的图是无向的，因此我们将其可视化
    nx_G = G.to_networkx().to_undirected()
    # Kamada-Kawaii布局对于一般的图通常看起来很漂亮
    pos = nx.kamada_kawai_layout(nx_G)
    nx.draw(nx_G, pos, with_labels=True, node_color=[[.7, .7, .7]])

    # 添加节点特征
    # 在DGL中，可以使用特征张量一次为所有节点添加特征
    # 沿第一个维度批处理节点特征。 下面的代码添加了可学习的内容
    # 所有节点的嵌入：

    embed = nn.Embedding(34, 5)  # 生成生成节点嵌入层，将34个节点嵌入到5个维度中。nn.Embedding：pytorch的嵌入层
    G.ndata['feat'] = embed.weight

    # 打印出2号节点的特征值
    print(G.ndata['feat'][2])

    # 打印出10、11号节点的特征值
    print(G.ndata['feat'][[10, 11]])