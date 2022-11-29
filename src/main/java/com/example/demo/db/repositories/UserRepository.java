package com.example.demo.db.repositories;

        import com.example.demo.db.entities.User;
        import org.springframework.data.repository.CrudRepository;


// просто операции с БД
public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUsername(String username);
}




