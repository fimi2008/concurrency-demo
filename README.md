# concurrency-demo
    并发和高并发学习

# 线程封闭
    
    Ad-hoc 线程封闭: 程序控制实现,最糟糕,忽略
    堆栈封闭: 局部变量,无并发问题
    ThreadLocal线程封闭: 特别好的封闭方法
    
# 线程安全-同步容器
    ArrayList -> Vector,Stack
    HashMap -> HashTable(key,value 不能为null)
    Collections.synchronizedXXX(List,Set,Map)

# 线程安全-并发容器J.U.C
    ArrayList -> CopyOnWriteArrayList
        使用场景：读多写少的并发场景
        使用注意点：
           1. 减少扩容开销。根据实际需要，初始化CopyOnWriteMap的大小，避免写时CopyOnWriteMap扩容的开销。
           2. 使用批量添加。因为每次添加，容器每次都会进行复制，所以减少添加次数，可以减少容器的复制次数。
        缺点：
           1.内存占用问题。
           2.数据一致性问题。CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。
    HashSet、TreeSet -> CopyOnWriteArraySet、ConcurrentSkipListSet
        
# AbstractQueuedSynchronizer-AQS
    并发容器中的同步器
    1.使用Node实现FIFO队列，可以用于构建锁或者其他同步装置的基础框架  
    2.利用一个int类型表示状态
    3.使用方法是继承 
    4.子类通过继承并通过实现它的方法管理其状态{acquire和release}的方法操纵状态
    5.可以同时实现排它锁和共享锁模式（独占、共享） 
    
    AQS同步组件
    1.CountDownLatch
    2.Semaphore 信号量 --> 进行并发访问控制
    3.CyclicBarrier
    4.ReentrantLock (可重入锁)
        独有功能：
            1.可指定是公平锁还是非公平锁
            2.提供一个Condition类，可以分组唤醒需要唤醒的线程
            3.提供能够中断等待锁的线程的机制，lock.lockInterruptibly()
    5.Condition
    6.FutureTask
    
# 线程池的合理配置
    CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 NCPU+1
    IO密集型任务，参考值可以设置为2*NCPU
    
# 死锁
    必要条件
        1.互斥条件
        2.请求和保持条件
        3.不剥夺条件
        4.环路等待条件
    
    