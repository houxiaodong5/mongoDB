package com.hxd.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
/**
 *          MongoDB demo 及常用查询语法
 * */
public class MongoDBJDBC {
    public static void main( String args[] ){
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("military");
            System.out.println("Connect to database successfully");
            MongoCollection<Document> collection = mongoDatabase.getCollection("knowledge_base");
            System.out.println("集合 test 选择成功");
            /**
             *  collection.find().limit(5);   查询指定条数
             *  FindIterable<Document> documents=collection.find().sort(BsonDocument.parse("{'名称':1}"));    //排序:1,正序；-1，倒叙
             *  FindIterable<Document> documents1=collection.find(BsonDocument.parse("{'名称':'九二式侦察机'}"));   //条件查询
             *  FindIterable<Document> documents=collection.find(BsonDocument.parse("{$or:[{'名称':'九二式侦察机'},{'产国': '德国'}]}"));   //按条件查询  or选择查询
             *  FindIterable<Document> documents=collection.find(BsonDocument.parse("{'名称':'九二式侦察机'},{'产国': '日本'}"));   //按条件查询  and查询
             *  FindIterable<Document> documents=collection.find(BsonDocument.parse("{'发动机数量':'双发'},$or:[{'名称':'九二式侦察机'},{'产国': '德国'}]"));  //AND 和 OR 联合使用:
             *  AND 和 OR 联合使用:
             *  SQL 语句为： 'where likes>50 AND (by = '菜鸟教程' OR title = 'MongoDB 教程')'
             *  db.col.find({"likes": {$gt:50}, $or: [{"by": "菜鸟教程"},{"title": "MongoDB 教程"}]}).pretty();

             *FindIterable<Document> documents = collection.find(); //查询所有
             *
             **/
            FindIterable<Document> documents = collection.find();
            System.out.println(collection.count());
            for(Document doc:documents){
                System.out.println(doc.toJson());
            }
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
