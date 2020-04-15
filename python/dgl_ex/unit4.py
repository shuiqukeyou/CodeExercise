import networkx as nx
import matplotlib.pyplot as plt
import torch
import dgl


# 消息传递（实现PageRank算法）
N = 100  # number of nodes
DAMP = 0.85  # damping factor
K = 10  # number of iterations
g = nx.nx.erdos_renyi_graph(N, 0.1)
g = dgl.DGLGraph(g)
nx.draw(g.to_networkx(), node_size=50, node_color=[[.5, .5, .5,]])
plt.show()

g.ndata['pv'] = torch.ones(N) / N
g.ndata['deg'] = g.out_degrees(g.nodes()).float()


# 消息传递函数：每个节点的pagerank值/出度
def pagerank_message_func(edges):
    return {'pv' : edges.src['pv'] / edges.src['deg']}


# 从mailbox中删除并聚合其消息，并且计算新pagerank值
def pagerank_reduce_func(nodes):
    msgs = torch.sum(nodes.mailbox['pv'], dim=1)
    pv = (1 - DAMP) / N + DAMP * msgs
    return {'pv' : pv}


# 在图中注册这两个方法
g.register_message_func(pagerank_message_func)
g.register_reduce_func(pagerank_reduce_func)


# 迭代过程
def pagerank_naive(g):
    # 第一步：沿边发送所有信息
    for u, v in zip(*g.edges()):
        g.send((u, v))
    # 第二步：接收消息以计算新的PageRank值。
    for v in g.nodes():
        g.recv(v)


