
OOP: 面向对象编程，用程序来描述事物

IOC: 控制反转 把创建对象的权利反转给 Spring（BeanFactory），我们只需要从IOC容器中获取就行

DI: 依赖注入 DL依赖查找 主要就是给创建的对象的属性赋值 三种 构造器赋值 set方法 直接赋值（反射）

AOP: 面向切面编程，找出多个bean中 重复、相同的代码，开发时将其抽出来，在运行时动态的插到对应的位置

    1、Aspect（切面）：通常是一个类，里面定义切入点和通知
    2、JointPoint（连接点）：程序执行过程中明确的点，一般是方法的调用
    3、Advice（通知）：AOP在切入点上执行的增强处理，before、after、afterReturning、afterThrowing、around
    4、Pointcut（切入点）：就是带有通知的连接点。切入点表达式所表示的那些方法