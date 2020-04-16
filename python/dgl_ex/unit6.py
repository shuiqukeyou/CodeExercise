import dgl


# 异构图

# 字典的每个值都是边元组的列表。
# 节点是从零开始的整数ID。 不同类型的节点ID具有单独的计数。
ratings = dgl.heterograph(
    {('user', '+1', 'movie') : [(0, 0), (0, 1), (1, 0)],
     ('user', '-1', 'movie') : [(2, 1)]})


# 创建异构图的几种数据源
# 通过稀疏张量
import scipy.sparse as sp
plus1 = sp.coo_matrix(([1, 1, 1], ([0, 0, 1], [0, 1, 0])), shape=(3, 2))
minus1 = sp.coo_matrix(([1], ([2], [1])), shape=(3, 2))
ratings = dgl.heterograph(
    {('user', '+1', 'movie') : plus1,
     ('user', '-1', 'movie') : minus1})

# 通过networkx对象
import networkx as nx
plus1 = nx.DiGraph()
plus1.add_nodes_from(['u0', 'u1', 'u2'], bipartite=0)
plus1.add_nodes_from(['m0', 'm1'], bipartite=1)
plus1.add_edges_from([('u0', 'm0'), ('u0', 'm1'), ('u1', 'm0')])

# 为了简化示例，重用了minus1对象。
# 即可以对不同的关系使用不同的图形数据源。
ratings = dgl.heterograph(
    {('user', '+1', 'movie') : plus1,
     ('user', '-1', 'movie') : minus1})

# 通过边列表创建
ratings = dgl.heterograph(
    {('user', '+1', 'movie') : ([0, 0, 1], [0, 1, 0]),
     ('user', '-1', 'movie') : ([2], [1])})


# 加载在线数据
# 数据集按其类型存储节点信息：P用于纸张，A 用于作者，C用于会议，L用于主题代码，等等。
# 这些关系存储为key下的SciPy稀疏矩阵XvsY，其中X和Y 可以是任何节点类型代码。
import scipy.io
import urllib.request

data_url = 'https://data.dgl.ai/dataset/ACM.mat'
data_file_path = '/tmp/ACM.mat'

urllib.request.urlretrieve(data_url, data_file_path)
data = scipy.io.loadmat(data_file_path)
print(list(data.keys()))

# 打印出一些数据信息
print(type(data['PvsA']))
print('#Papers:', data['PvsA'].shape[0])
print('#Authors:', data['PvsA'].shape[1])
print('#Links:', data['PvsA'].nnz)

# 通过储存的稀疏矩阵构建数据
pa_g = dgl.heterograph({('paper', 'written-by', 'author') : data['PvsA']})
# 等价（较短）的API，用于创建具有两种节点类型的异形图：
pa_g = dgl.bipartite(data['PvsA'], 'paper', 'written-by', 'author')


print('Node types:', pa_g.ntypes)
print('Edge types:', pa_g.etypes)
print('Canonical edge types:', pa_g.canonical_etypes)

# 节点和边是从0开始的整数ID
# 要区分不同类型的节点和边，需要指定类型名称作为参数。
print(pa_g.number_of_nodes('paper'))
# 如果规范边缘类型名称是唯一可区分的，则可以将其缩写为一个边缘类型名称。
print(pa_g.number_of_edges(('paper', 'written-by', 'author')))
print(pa_g.number_of_edges('written-by'))
print(pa_g.successors(1, etype='written-by'))  # 得到论文＃1的作者

# 只要行为明确，就可以省略类型名称参数。
print(pa_g.number_of_edges())  # 仅一种边缘类型，可以省略edge type参数


# 同构图是一种特殊的异构图
# 论文间引用图是同构图
pp_g = dgl.heterograph({('paper', 'citing', 'paper') : data['PvsP']})
# 用于创建同构图的等效（较短）API
pp_g = dgl.graph(data['PvsP'], 'paper', 'cite')

# 所有ntype和etype参数都可以省略，因为行为是明确的。
print(pp_g.number_of_nodes())
print(pp_g.number_of_edges())
print(pp_g.successors(3))

G = dgl.heterograph({
        ('paper', 'written-by', 'author') : data['PvsA'],
        ('author', 'writing', 'paper') : data['PvsA'].transpose(),
        ('paper', 'citing', 'paper') : data['PvsP'],
        ('paper', 'cited', 'paper') : data['PvsP'].transpose(),
        ('paper', 'is-about', 'subject') : data['PvsL'],
        ('subject', 'has', 'paper') : data['PvsL'].transpose(),
    })

print(G)

# 使用graphviz绘制元图。
import pygraphviz as pgv
def plot_graph(nxg):
    ag = pgv.AGraph(strict=False, directed=True)
    for u, v, k in nxg.edges(keys=True):
        ag.add_edge(u, v, label=k)
    ag.layout('dot')
    ag.draw('graph.png')

plot_graph(G.metagraph)
