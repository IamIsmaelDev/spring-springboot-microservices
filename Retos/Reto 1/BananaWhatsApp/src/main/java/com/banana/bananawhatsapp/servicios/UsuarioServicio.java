package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServicio implements IServicioUsuarios{

    @Autowired
    private IUsuarioRepository repository;

    @Override
    public Usuario obtener(Long id) throws UsuarioException, SQLException {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws UsuarioException {
        Optional<Usuario> usuario1 = repository.findByEmail(usuario.getEmail());
        if(usuario1.isEmpty()){
            return repository.save(usuario);
        }else{
            throw new UsuarioException();
        }
    }

    @Override
    public boolean borrarUsuario(Usuario usuario) throws UsuarioException {
        try{
            Optional<Usuario> usuario1 = repository.findById(usuario.getId());
            if (usuario1.isEmpty()){
                return UsuarioException();
            }
            repository.delete(usuario);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return UsuarioException();
        }
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException {
        try{
            Optional<Usuario> usuario1 = repository.findById(usuario.getId());
            if(usuario1.isEmpty()){
                return UsuarioException();
            }
            return repository.save(usuario);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return UsuarioException();
        }
    }

    @Override
    public Set<Usuario> obtenerPosiblesDesinatarios(Usuario usuario, int max) throws UsuarioException, SQLException {
        try{
            Optional<Usuario> usuario1 = repository.findById(usuario.getId());
            if(usuario1.isEmpty()){
                return UsuarioException();
            }
            return repository.obtenerPosiblesDestinatarios(usuario.getId(), max);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return UsuarioException();
        }
    }
}
