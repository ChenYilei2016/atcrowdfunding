# atcrowdfunding

整合了通用mapper 更加简洁

血的教训1: 随时 commit
2 : 放入 log4j.properties 文件  弄了一天多 就是因为没日志的缘故,放了日志 10秒找出原因... 

# DEBUG < INFO < WARN < ERROR < FATAL
log4j.rootLogger=INFO, stdout


log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss S} %5p %c:%l - %m%n
#log4j.logger.org.hibernate=INFO

#这是一个传统分模块的SSM项目. 学习工作流的使用(暂未完成)
