package com.example.demo.service;


import com.example.demo.db.entities.User;
import com.example.demo.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// сервис для работы с таблицей пользователей
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    // добавление нового пользователя
    public void addUser(String username, String password){
        User user = new User();
        user.setUsername(username);

        // интерфейс для хэширования и проверки
        password = encoder.encode(password);    // захэшировать пароль
        user.setPassword(password);     // пароль необходимо хэшировать
        userRepository.save(user);      // сохранил пользователя
    }

    // метод получения объекта пользователя
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
