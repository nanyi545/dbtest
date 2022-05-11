package com.example.spring1.mappers;

import com.example.spring1.data.Item;
import com.example.spring1.data.Office;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {


    @Select("SELECT * FROM items")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "item_no", column = "item_no"),
    })
    List<Item> getAll();


    @Select("SELECT * FROM items where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "item_no", column = "item_no"),
    })
    Item getById(int id);


    @Insert("insert into items(item_no) VALUES(#{item_no})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Item item);


    @Delete("delete from items where id = #{id}")
    int delete ( int id );


}
