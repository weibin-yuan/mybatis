package com.ywb.dao;

import com.ywb.entity.Book;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;

public class BookDao {
    //使用类加载器加载mybatis的配置文件
    InputStream inputStream = BookDao.class.getClassLoader().getResourceAsStream("config.xml");
    //构建sqlSession的工厂
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    public  Book getBookById(Integer id) {
        //创建能执行映射文件中的sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 操作CRUD，第一个参数：指定statement，规则：命名空间+“.”+statementId
            // 第二个参数：指定传入sql的参数：这里是用户id
            Book book = sqlSession.selectOne("com.ywb.dao.BookMapper.selectBookById", id);
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
    public void del(Integer id) {
        //创建能执行映射文件中的sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            sqlSession.selectOne("com.ywb.dao.BookMapper.del", id);
        }finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
    }
    public void add(Book book) {
        //创建能执行映射文件中的sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            sqlSession.selectOne("com.ywb.dao.BookMapper.add", book);
        }finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
    }
    public void update(Book book) {
        //创建能执行映射文件中的sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            sqlSession.selectOne("com.ywb.dao.BookMapper.update", book);
        }finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
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
        bookDao.del(1);

        Book book = new Book();
        book.setName("小明");
        book.setNumber(456);
        bookDao.add(book);

        Book book1 = new BookDao().getBookById(2);
        book1.setName("小红");
        bookDao.update(book1);

    }
}