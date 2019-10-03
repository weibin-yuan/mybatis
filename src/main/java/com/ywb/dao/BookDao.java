package com.ywb.dao;

import com.ywb.entity.Book;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;

public class BookDao {
    public  Book getBookById(int i) {
        //使用类加载器加载mybatis的配置文件
        InputStream inputStream = BookDao.class.getClassLoader().getResourceAsStream("config.xml");
        //构建sqlSession的工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //创建能执行映射文件中的sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Book book = sqlSession.selectOne("com.ywb.dao.BookMapper.selectBookById", 2);
            return book;
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("成功catch");
        }finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
        return null;
    }
    public static void main(String[] args) {
        BookDao bookDao=new BookDao();
        long start=System.currentTimeMillis();
        for (int i = 0; i<1001; i++) {
            bookDao.getBookById(1);
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
//        System.out.println(bookDao.getBookById(1));

    }
}