import tensorflow as tf

a = tf.constant(120, name = "a")
b = tf.constant(130, name = "b")
c = tf.constant(140, name = "c")

v = tf.Variable(0,name = "v")

calc_op = a + b + c
assign_op = tf.assign(v, calc_op)

sess = tf.Session()
sess.run(assign_op)

print(sess.run(v))