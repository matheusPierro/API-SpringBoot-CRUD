package br.com.project.api.services;

import br.com.project.api.models.Message;
import br.com.project.api.models.Person;
import br.com.project.api.repository.Repositories;
import jdk.jfr.Percentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Services {

    @Autowired
    private Message message;

    @Autowired
    private Repositories action;

    public ResponseEntity<?> register(Person obj) {
        if (obj.getName().equals("")) {
            message.setMessage("O nome precisa ser preenchido");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (obj.getAge() < 0) {
            message.setMessage("Informe uma idade válida");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(action.save(obj), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> selectAll() {
        return new ResponseEntity<>(action.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> selectByCode(int code) {
        if (action.countByCode(code) == 0) {
            message.setMessage("Não foi encontrada nenhuma pessoa.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(action.findByCode(code), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> edit(Person obj) {
        if (action.countByCode(obj.getCode()) == 0) {
            message.setMessage("O código informado não existe.");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (obj.getName().equals("")) {
            message.setMessage("É necessário informar o nome.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (obj.getAge() < 0) {
            message.setMessage("Informe uma idade válida.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(action.save(obj), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> remove(int code) {
        if (action.countByCode(code) == 0) {
            message.setMessage("O código informado não existe.");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }else {
            Person obj = action.findByCode(code);
            action.delete(obj);
            message.setMessage("Pessoa removida com sucesso!");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
