import networkx as nx
import dgl
import numpy as np
import torch as th
import scipy.sparse as spp
import matplotlib.pyplot as plt

# 创建图的方式
"""
有很多方法可以构建一个DGLGraph:
    - 一对分别存储源节点和目标节点的数组。它们可以是后端框架中的numpy数组或张量对象(u, v)
    - scipy 稀疏矩阵，表示要构造的图的邻接矩阵。
    - networkx 图形对象。
    - 整数对形式的边列表。
"""
# 从networks对象创建dgl图
g_nx = nx.petersen_graph()  # 创建一个networks内置的petersen图
g_dgl = dgl.DGLGraph(g_nx)


plt.subplot(121)
nx.draw(g_nx, with_labels=True)
plt.subplot(122)
nx.draw(g_dgl.to_networkx(), with_labels=True)

plt.show()


# 从一对数组创建一个星形图（也可以使用numpy.array）。
u = th.tensor([0, 0, 0, 0, 0])
v = th.tensor([1, 2, 3, 4, 5])
star1 = dgl.DGLGraph((u, v))

# 一次性创建相同的图 需要其中一个数组是标量，
# 该值会被自动广播以匹配另一个数组的长度
# 这是一种称为“边缘广播”的机制
start2 = dgl.DGLGraph((0, v))

# 从scipy稀疏矩阵创建相同的图形（也可以使用scipy.sparse.csr_matrix）。
adj = spp.coo_matrix((np.ones(len(u)), (u.numpy(), v.numpy())))
star3 = dgl.DGLGraph(adj)

# 从整数对列表中创建相同的图。
elist = [(0, 1), (0, 2), (0, 3), (0, 4), (0, 5)]
star4 = dgl.DGLGraph(elist)


# 在已创建的图上逐渐添加更多的节点和边

g = dgl.DGLGraph()  # 创建图
g.add_nodes(10)  # 添加十个节点
# 一对一的添加每条边:1~4连接到0
for i in range(1, 4):
    g.add_edge(i, 0)

# 通过配对的list添加：[5,6,7]、[0,0,0]
src = list(range(5, 8))
dst = [0]*3
g.add_edges(src, dst)

# 通过一对张量创建边：8、9连接到0，0
src = th.tensor([8, 9])
dst = th.tensor([0, 0])
g.add_edges(src, dst)


# 通过边缘广播一次性创建星形图
g.clear()  # 清空图g
g.add_nodes(10)  # 创建十个节点
src = th.tensor(list(range(1, 10)))
g.add_edges(src, 0)  # 边缘广播

# 图可视化
nx.draw(g.to_networkx(), with_labels=True)
plt.show()


# 将特征分配给DGLGraph的节点和边
x = th.randn(10, 3)
# ndata是用于访问所有节点的特征数据的语法糖
# 使用这个语法糖之后可以方便的进行切片等各种操作，以下为通过切片直接修改数据
g.ndata['x'] = x  # 新增节点数据，并将其命名为x
g.ndata['x'][0] = th.zeros(1, 3)  # 修改一个点
g.ndata['x'][[0, 1, 2]] = th.zeros(3, 3)  # 通过list修改三个点
g.ndata['x'][th.tensor([0, 1, 2])] = th.randn((3, 3))  # 通过tensor修改

# 同理，edata是用于访问所有边的特征数据的语法糖
g.edata['w'] = th.randn(9, 2)  # 新增边数据并将其命名为w

# 使用以整数，列表或整数张量设置的ID访问边缘
g.edata['w'][1] = th.randn(1, 2)
g.edata['w'][[0, 1, 2]] = th.zeros(3, 2)
g.edata['w'][th.tensor([0, 1, 2])] = th.zeros(3, 2)

# 可以通过提供端点来获取边缘ID，这对于访问特征很有用。
# 以下为三种访问方式
g.edata['w'][g.edge_id(1, 0)] = th.ones(1, 2)                   # edge 1 -> 0
g.edata['w'][g.edge_ids([1, 2, 3], [0, 0, 0])] = th.ones(3, 2)  # edges [1, 2, 3] -> 0
# 使用边缘广播。
g.edata['w'][g.edge_ids([1, 2, 3], 0)] = th.ones(3, 2)          # edges [1, 2, 3] -> 0

print(g.node_attr_schemes())
g.ndata['x'] = th.zeros((10, 4))  # 新增一个点
print(g.node_attr_schemes())

# 删除节点和边
g.ndata.pop('x')
g.edata.pop('w')

# 在多重图上工作（多重图：两个节点间允许有多重边的图）
# 以下的节点和边的操作都是同时修改多重图
g_multi = dgl.DGLGraph()
g_multi.add_nodes(10)
g_multi.ndata['x'] = th.randn(10, 2)

g_multi.add_edges(list(range(1, 10)), 0)
g_multi.add_edge(1, 0)  # 添加第二条1到0的边

g_multi.edata['w'] = th.randn(10, 2)
g_multi.edges[1].data['w'] = th.zeros(1, 2)
print(g_multi.edges())

# 在多重图上不能使用入射节点来唯一化的标识u和v，需要使用edge_id获取其边id
eid_10 = g_multi.edge_id(1, 0, return_array=True)
g_multi.edges[eid_10].data['w'] = th.ones(len(eid_10), 2)
print(g_multi.edata['w'])