import tensorflow as tf
a = tf.placeholder(tf.int32, [None])

b = tf.constant(10)
x10_op = a*b

sess = tf.Session()

r1 = sess.run(x10_op, feed_dict={a:[1,2,3,4,5]})
r2 = sess.run(x10_op, feed_dict={a:[10,20,30,40,50,]})

print(r1)
print(r2)