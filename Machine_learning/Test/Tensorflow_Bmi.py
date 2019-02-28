import tensorflow as tf

testNum1 = [1,5,0,4,3,2,8,0,3,2]
testNum2 = [3,6,1,5,4,3,1,5,5,3]
answer = [4,11,1,9,7,5,9,5,8,5]

x = tf.placeholder(tf.float32, [None,2])
y_ = tf.placeholder(tf.float32, [None,1])

W = tf.Variable([2,1])
b = tf.Variable([2])
y = tf.nn.softmax(tf.matmul(x,W)+b)

cross_entropy = -tf.reduce_sum(y_*tf.log(y))
optimizer = tf.train.GradientDescentOptimizer(0.01)
train = optimizer.minimize(cross_entropy)

predict = tf.equal(tf.arg_max(y,1),tf.arg_max(y_,1))
accuracy = tf.reduce_mean(tf.cast(predict, tf.float32))

sess = tf.Session()
sess.run(tf.global_variables_initializer())

for step in range(3500):
    rows = [testNum1,testNum2]
    y_ans = answer
    fd = {x:rows,y_:y_ans}
    sess.run(train, feed_dict=fd)
    if step % 50 == 0:
        cre = sess.run(cross_entropy, feed_dict=fd)
        acc = sess.run(accuracy,feed_dict={x:rows})
        print("step=",step,"acc=",acc,"cre=",cre)
