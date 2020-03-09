import tensorflow as tf
from unit5 import MNISTLoader, MLP


# 训练过程可视化
# 需要在项目目录下创建一个记录用的文件夹，路径需全英文，此处为./tensorboard
# 需要可视化时，在项目根目录执行“tensorboard --logdir=./tensorboard”（启动本地可视化服务器）
# http:// localhost：6006

# 设定运行参数
num_epochs = 5
batch_size = 50
learning_rate = 0.001

# 创建模型、数据获取器、优化器
model = MLP()
data_loader = MNISTLoader()
optimizer = tf.keras.optimizers.Adam(learning_rate=learning_rate)

"""初始化可视化记录器"""
summary_writer = tf.summary.create_file_writer('./tensorboard')     # 参数为记录文件所保存的目录
tf.summary.trace_on(graph=True, profiler=True)  # 开启Trace（可选），用于查看计算图

# 训练（含可视化记录）
num_batches = int(data_loader.num_train_data // batch_size * num_epochs)  # 获取总共可用的batch数
for batch_index in range(num_batches):
    X, y = data_loader.get_batch(batch_size)
    with tf.GradientTape() as tape:
        y_pred = model(X)
        loss = tf.keras.losses.sparse_categorical_crossentropy(y_true=y, y_pred=y_pred)  # 交叉熵损失
        loss = tf.reduce_mean(loss)  # reduce_mean：沿指定维度计算均值，默认为维度0

        # 可视化记录，因为记录的是loss，故需要在loss的有效域内进行
        with summary_writer.as_default():  # 希望使用的记录器
            tf.summary.scalar("loss", loss, step=batch_index)

        print("batch %d: 损失 %f" % (batch_index, loss.numpy()))
    grads = tape.gradient(loss, model.variables)  # 自动获得每个变量对于loss 的梯度
    optimizer.apply_gradients(grads_and_vars=zip(grads, model.variables))

# 绘制计算图在训练完成后
with summary_writer.as_default():
    tf.summary.trace_export(name="model_trace", step=0, profiler_outdir="tensorboard")    # 保存Trace信息到文件（可选）




