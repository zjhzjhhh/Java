# Mybatis



## Maven的项目结构



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608218.png)



### pom.xml



 

1. pom指的是 project object model
2. Maven可以根据pom.xml文件的配置对此项目进行依赖管理

 



example：



```xml
<?xml version="1.0" encoding="utf-8"?>
<project xml=命名规则>
    <!--指定项目模型版本-->
    <modelVersion>4.0</modelVersion>
    <!--指定项目的表示，GAV...G:企业表示 A:项目表示 V:项目版本-->
    <groupId>com.zjhzjhhh.www</groupId>
    <artifactId>zjhzjhhh</artifactId>
    <version>1.0.0</version>
    
    
    <!--当前项目依赖配置-->
    <!--https://mvnrepository.com/-->
    <dependencies></dependencies>
</project>
```



**配置依赖：**找到依赖坐标，添加到dependencies标签中



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608606.png)



找到需要的jar包文件，找到Maven依赖坐标，添加到dependencies标签。



## Maven如何进行依赖管理



![image-20220531160937379](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311609455.png)



### Maven仓库配置



在maven_home/conf/settings.xml中配置



- **本地仓库**



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608396.png)



- **公共仓库**



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608866.png)



## 基于IDEA中的Maven配置与使用



- **在IDEA中关联Maven**



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608403.png)



## lombok学习



lombok是一个java开发插件，可以使用一些注解来省略一些重复的代码主要针对简单的Java模型对象（POJO）。



### [@Data ]() 



该注解提供类所有属性的getter/setter方法，还提供了equals，canEqual，hashCode、toString方法。



```java
//示例：
@Data
public class Demo {
	private int id;
	private String remark;
}


//使用@Data编译以后
public class Demo {
    private int id;
    private String remark;

    public Demo() {
    }

    public int getId() {
        return this.id;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Demo)) {
            return false;
        } else {
            Demo other = (Demo)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getId() != other.getId()) {
                return false;
            } else {
                Object this$remark = this.getRemark();
                Object other$remark = other.getRemark();
                if (this$remark == null) {
                    if (other$remark != null) {
                        return false;
                    }
                } else if (!this$remark.equals(other$remark)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Demo;
    }

    public int hashCode() {
        int PRIME = true;
        int result = 1;
        int result = result * 59 + this.getId();
        Object $remark = this.getRemark();
        result = result * 59 + ($remark == null ? 43 : $remark.hashCode());
        return result;
    }

    public String toString() {
        return "Demo(id=" + this.getId() + ", remark=" + this.getRemark() + ")";
    }
}
```



### [@Log4j ]() 



作用于类上，为该类提供一个属性名为log的log4j日志对象。



```plain
@Log4j
public class Demo {
}
```



该属性一般使用于Controller、Service等业务处理类上。与此注解相同的还有@Log4j2，顾名思义，针对Log4j2。



### [@AllArgsConstructor ]() 



作用于类上，为该类提供一个包含全部参的构造方法，注意此时默认构造方法不会提供。



```plain
@AllArgsConstructor
public class Demo {
	private int id;
	private String remark;
}
```



效果如下：



```java
public class Demo {
    private int id;
    private String remark;

    public Demo(final int id, final String remark) {
        this.id = id;
        this.remark = remark;
    }
}
```



### [@NoArgsConstructor ]() 



作用于类上，提供一个无参的构造方法。可以和@AllArgsConstructor同时使用，此时会生成两个构造方法：无参构造方法和全参构造方法。



### [@EqualsAndHashCode ]() 



作用于类上，生成equals、canEqual、hashCode方法。具体效果参看最开始的@Data效果。



### [@NonNull ]() 



作用于属性上，提供关于此参数的非空检查，如果参数为空，则抛出空指针异常。



## 案例学习 学生信息系统



1. **创建数据库**



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608663.png)



1. **创建实体类(使用pojo)**



```java
package com.zjhzjhhh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private int stuId;
    private String stuNum;
    private String strName;
    private String StuGender;
    private String StuAge;

}
```



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608123.png)



1. **创建Dao接口，定义操作方法**



```java
package com.zjhzjhhh.dao;

import com.zjhzjhhh.pojo.Student;

public interface StudentDAO {

    public int insertStudent(Student student);
    
}//这个接口目前没有配置文件不知道实现类是哪个，所以我们现在需要完成一个配置文件
```



1. 创建DAO接口的映射文件



-  在resources目录下新建名为mappers文件夹 
-  在mappers中新建名为StudentMapper.xml(这个mapper文件相当于Dao层文件接口的实现类) 
-  在映射文件中华对DAO中定义的方法进行实现： 



```xml
//StudentMapper.xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--mapper文件相当于DAO接口的实现类，namespace属性要指定，namespace=”DAO的文件全名“-->
<mapper namespace="com.zjhzjhhh.dao.StudentDAO">

    <insert id="insertStudent" parameterType="com.zjhzjhhh.pojo.Student">
        insert into tb_student(stu_nam,stu_name,stu_gender,stu_age) values(#{stuNum},#{stuName},#{stuGender},#{stuAge})
    </insert>
    <delete id="deleteStudent">
        delete from tb_students where stu_num=#{stuNum}
    </delete>

</mapper>
```



通俗一点的来讲:



1. mapper.xml文件相当于DAO文件的实现类，所以在namespace处要写DAO接口的全限定名(包名+文件名).
2. 然后再看在接口中我们需要实现增删改查什么方法，如果是增加就是用insert标签，以此类推，删除就是用delete标签，修改就是用update标签，查询就是用select标签。
3. 其中id要跟接口中的类名一致。
4. parameterType指的是id对应的方法需要什么参数类型，因为我们需要从传进来的对象中拿到参数的值。
5. `#{}`这个相当于预编译中的`?`,可以理解为是个占位符。
6. mapper文件可以理解为lmpl文件。



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608826.png)



1. 映射文件需要添加到主配置文件(mybatis-config.xml）

| mybatis-config.xml                                           |
| ------------------------------------------------------------ |
| ![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608503.png) |



## 单元测试



1. 创建单元测试类



在被测试类名后鼠标右键Generate选择Test



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608548.png)



对于增删改查四个功能中，添加和删除使用mybatis的思路



```java
            //加载mybatis配置文件,使用mybatis自带的Resources模块得到xml文件里的配置信息，最为输出流给is
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            //SqlSessionFactoryBuilder 创建工厂模式
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            //会话工厂 mybatis的会话工厂
            SqlSessionFactory factory = builder.build(is);

            //会话(连接) SqlSession表示mybatis与数据库之间的会话，通过工厂方法设计模式
            SqlSession sqlSession = factory.openSession();
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);//调用StudentDAO这个接口，我们需要获取StudentDAO对象

            int i = studentDAO.insertStudent(new Student());
            sqlSession.commit();
            System.out.println(studentDAO);
```



需要加上异常捕获



增：



![img](https://gitee.com/zjhzjhhh/photo/raw/master/202110271348154.png)



删：



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608360.png)



改：



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608769.png)



查询所有：



1. 在SutdentDAO定义一个list属性的变量



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608443.png)



![image-20220531161017949](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311610032.png)



查询单条信息



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608385.png)



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608375.png)



## mybatis工具类封装



在很多操作方法中有很多多余的代码



封装工具类utils



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608314.png)



```java
package com.zjhzjhhh.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class MybatisUtil {


    private static SqlSessionFactory factory;
    private static final ThreadLocal<SqlSession> LOCAL = new ThreadLocal<SqlSession>();


    //static部分得到SqlSessionFactory对象
    static {

        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //会话工厂
            factory = builder.build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    //getSession部分得到SqlSession对象
    public static SqlSession getSqlsession() {

        SqlSession sqlSession = LOCAL.get();
        if (sqlSession == null){
            sqlSession = factory.openSession();
            LOCAL.set(sqlSession);
        }

        return factory.openSession();
    }
}
```



封装工具类就是死东西，不会有改变或者过多的改变。



工具类封装好以后，想这些红框框里的东西都可以不要了，转而全部调用util层即可



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608972.png)



![image-20220531161114496](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311611660.png)



这是封装了Sqlsession类相关的代码，还有一部分是所有功能点他都要使用到mapper部分，所以封装mapper工具类



### 封装mapper工具类



```java
    //mapper工具类   T形参继承Object中的所有属性，返回值为T类型
    public static <T extends Object>T getMapper(Class<T> c){
        SqlSession sqlSession = getSqlsession();
        T dao = sqlSession.getMapper(c);


    }
```



### java泛型



泛型：参数化类型。 将原本的具体类型参数化。举例



public class GenericTest {



```java
public static void main(String[] args) {

    List<String> list = new ArrayList<String>();
    list.add("a2rcher");
    list.add("123456");
    //list.add(100);   // 1  提示编译错误

    for (int i = 0; i < list.size(); i++) {
        String name = list.get(i); // 2
        System.out.println("name:" + name);
    }
}
```



通过List</String/>，直接限定了list集合中只能含有String类型的元素。其中String是类型实参



参考https://www.cnblogs.com/lwbqqyumidi/p/3837629.html



## 事务管理



SqlSession对象：

 

- getMapper(DAO.class)：获取Mapper(DAO接口实例)
- 事务管理

 



### 手动提交事务



- sqlSession.commit()
- sqlSession.rollback()



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608583.png)



# Spring IOC



spring项目部署



 

1. 搭建maven工程

 

![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608321.png)

 

1. 导入spring依赖 

- - core
  - beans
  - aop
  - expression
  - context(这一个包就够了)

 

1. 创建spring配置文件 

- - 通过配置文件告诉spring容器创建什么对象？给对象什么属性？赋什么值？
  - resources目录，创建appicationContext.xml文件

 



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608142.png)



## springIoC组件创建并管理对象



1. 创建一个实体类



```java
public class Student {
    private String stuNum;
    private String stuName;
    private String stGender;
    private int stuAge;
    private Date enterenceTime;
```



1. 在spring配置文件中配置实体类



在applicationContext.xml文件中配置



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608863.png)



1. 创建spring对象工厂获取对象



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608940.png)



```java
public class testSpring {

    public static void main(String[] args) {
        //通过spring创建student对象

        //1. 初始化spring容器，加载spring配置文件

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2. 通过spring容器获取spring对象.通过配置文件中的id找到类的唯一标识

        Object student2 = context.getBean("stu");

        System.out.println(student2);
    }
```



1. IOC与DI



-  IOC控制反转，通过spring对象工厂完成对象的创建 
-  DI依赖注入，在spring完成对象创建的同时依赖spring容器完成 
-  当我们需要通过spring对象工厂创建某个类的对象时候，需要将这个交给spring 



## 依赖注入



Spring容器加载配置文件后，通过反射创建类对象并给属性赋值

 

Spring容器通过反射实现属性注入有三种方式：

 

- set方法注入
- 构造器注入
- 接口注入

 



### set注入



在bean标签中通过配置property标签给属性赋值，实际上就是通过反射调用set方法完成属性注入。



1. 简单类型集字符串



```xml
<bean id="stu" class="com.zjhzjhhh.ioc.bean.Student" autowire="byName">
    <property name="stuNum" value="100"/>
    <property name="stuName" value="100"/>
    <property name="stuAge" value="231423"/>


</bean>
```



autowire自动判定唯一标识name是什么类型



1.  日期类型对象 

1. 1. 在property标签中通过ref引用spring容器中的一个对象

```xml
<bean id="date" class="java.util.Date"/>
<bean id="stu" class="com.zjhzjhhh.ioc.bean.Student">
    <property name="enterenceTime" ref="date"/>
</bean>
```

(记住就好了) 

1. 1. 在property标签中添加子标签bean来指定对象

```xml
<bean id="stu" class="com.zjhzjhhh.ioc.bean.Student">
    <property name="enterenceTime">
        <bean class="java.util.Date"/>
    </property>
</bean>
```

1.  自定义类
   假设我现在需要自定义类实现部分功能，那我在完成依赖注入是配置xml文件的方式跟Date有相同之处
   ![img](https://gitee.com/zjhzjhhh/photo/raw/master/202111021552117.png) 

1. 1. 在property标签中通过ref引用spring容器中的一个对象



```xml
<bean id="cla" class="com.zjhzjhhh.ioc.bean.Clazz">
    <property name="classId" value="2021"/>
    <property name="className" value="a2rcher"/>
</bean>
<bean id="cla" class="com.zjhzjhhh.ioc.bean.Student">
    <property name="clazz" ref="cla"/>
</bean>
```



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608767.png)







```plain
	2. 在property标签中添加子标签bean来指定对象
```



```xml
<bean id="cla" class="com.zjhzjhhh.ioc.bean.Student">
    <property name="clazz">
        <bean class="com.zjhzjhhh.ioc.bean.Clazz">
            <property name="className" value="a2rcher"/>
            <property name="classId" value="12312"/>

        </bean>
    </property>
</bean>
```



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608408.png)



1. 集合类型



### 构造器注入



简单类型，字符串类型，自定义类型。 index是索引，对应是哪一个变量对应的值



```xml
<!--    构造器注入-->
    <bean id="stu" class="com.zjhzjhhh.ioc.bean.Student">
        <constructor-arg index="0" value="1001"/>
        <constructor-arg index="1" value="ads"/>
        <constructor-arg index="2" value="1001"/>
        <constructor-arg index="3" value="1001"/>
        <constructor-arg index="4" value="1001"/>
        <constructor-arg index="5">
            <bean class="com.zjhzjhhh.ioc.bean.Clazz"></bean>
        </constructor-arg>


    </bean>
```



对于集合类型：



```xml
    <bean id="stu1" class="com.zjhzjhhh.ioc.bean.Student">
        <constructor-arg index="0">
            <list>
                <value>11</value>
                <value>22</value>
            </list>
        </constructor-arg>
        <constructor-arg index="1">
            <set>
                <value>aa</value>
                <value>bb</value>
            </set>
        </constructor-arg>
        <constructor-arg index="2">
            <map>
                <entry>
                    <key>
                        <value>key1</value>
                    </key>
                    <value>value1</value>
                </entry>
                <entry>
                    <key>
                        <value>key2</value>
                    </key>
                    <value>value2</value>
                </entry>
            </map>
        </constructor-arg>
        <constructor-arg index="3">
            <props>
                <prop key="k1">v1</prop>
                <prop key="k2">v2</prop>
            </props>
        </constructor-arg>
    </bean>
```



## spring Ioc注解



1. 创建Spring配置文件



- Spring容器初始化时，只会加载applicationContext.xml文件，那么我们在实体类添加的注解就不会被扫描到，所以在配置文件里面需要添加一个spring扫描范围，帮助spring初始化时扫描带有注解的实体类并完成初始化。



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context
         http://www.springframework.org/schema/context/spring-context.xsd">

<!--    生命使用注解配置-->
    <context:annotation-config/>


<!--    生命Spring工厂注解的扫描范围-->
    <context:component-scan base-package=""/>

</beans>
```



1. Ioc常用注解 

1. 1. [**@Component** ]()  

- - - 类注解，声明此类被Spring容器进行管理，相当于bean标签的作用
    - [@Component(value ]() = "stu") value属性用于指定当前bean的id，相当于bean标签的id属性；value属性也可以省略，默认时类的全小写。 
    - @Service，@Controller，@Repository这三个注解也可以将类生命给spring管理xml，三者的区别在于语义上的区别。 

- - - - [@Controller	]() 注解主要声明将控制器类配置给Spring管理，例如Servlet 
      - [@Service ]()  注解主要生命业务处理类配置Spring管理，Service接口的实现类 
      - [@Repository ]() 主要声明持久化配置给Spring管理，DAO接口 
      - [@Component ]() 除了控制器，service，DAO以外的类一律使用此注解声明 

1. 1. [**@Scope** ]()  

- - - 类注解，用于声明当前类单例模式还是非单例模式，相当于bean标签中的scope属性
    - @Scope("prototype")表示声明当前类为非单例模式(默认为单例模式)

1. 1. @Lazy(false)表示是否声明为懒汉模式
   2. [**@PostConstruct** ]()  

- - - 方法注解，声明一个方法为当前类的初始话方法(在构造器之后执行)，相当于bean标签init-method属性

1. 1. [**@PreDestroy** ]()  

- - - 方法注解，声明一个方法为当前类销毁方法(在对象从容器中释放之前执行)，相当于bean标签的destory-method属性

1. 1. **Autowirted** 

- - - 属性注解，方法注解(set方法)，声明当前属性自动装配，默认byType
    - AutoWired(require=false) 通过requried属性设置当前自动装配是否为必须(默认必须 如果没有找到类型与属性类型匹配的bean则抛出异常) 

- - - - byTpye
      - ref引用

1. 1. [**@Resource** ]()  

- - - 属性注解，也属于声明属性自动装配
    - 默认装配方式为byName，如果根据byName没有找到对应的bean，则继续根据byType寻找对应的bean，根据byType如果依然没有找到Bean或者找到不止一个类型匹配的bean，则派出异常。



## 代理设计模式



代理设计模式优点：将通用性的工作交给代理对象完成，被代理对象只需专注自己的核心业务。



### 静态代理



### 动态代理



## Spring AOP



AOP：面向切面编程。是一种利用“横切”的技术对原有业务逻辑进行拦截，并且可以在这个拦截的横切面商添加特定的业务逻辑，对原有的业务进行增强。



通俗点讲：基于动态代理实现不改变原有业务情况下对业务逻辑增强。 解释一张图就非常好理解



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608894.png)



对于一个项目来说，他原有的业务逻辑其实是一条或者多条链子组成，SpringAOP在方法实现类那里使用到，连接点就是实现的方法类，在他的前后被Spring横切，用来增加新的业务逻辑功能，比方说像管理事务类里面的开启事务begin(),结束事务commt()，回滚rollback()，把这些配置新增业务的方法新增成一个类，这个类是自己构造出来的，我们管他叫切面类，其中的方法就叫切点。在逻辑运行中运行到功能点时，SpringAOP将其拦截，在之前开启事务，之后关闭事务，出现异常时回滚使其业务逻辑增强。



### Sping AOP案例



1. 配置文件



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608330.png)



1. 整合Druid连接池



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608042.png)



1. 在applicationContext.xml文件中配置DruidDataSource



## SPringMVC



在web.xml中提供一个DispatcherServlet的类(SpringMVC前端控制器)，用于拦截用户请求交由SpringMVC处理



1. 创建控制器



- 创建一个包让spring注解扫描的范围内
- 创建一个类
- 类上添加注解@Cntroller，声明此类为SpringMVC的控制器
- 类上添加注解@RequestMapping("/...")，声明此控制器类的请求url，类似于python中的路由



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608452.png)



1. 在控制器类中定义处理请求的方法



- 在一个控制器类中可以定义多个方法处理不同请求
- 在控制器类中的方法上也要添加@RequestMapping注解



![img](https://cdn.jsdelivr.net/gh/zjhzjhhh/image/blogs/picures/202205311608010.png)