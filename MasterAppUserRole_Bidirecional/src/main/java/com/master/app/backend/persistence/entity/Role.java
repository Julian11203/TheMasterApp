package com.master.app.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    // Esto se usa en casos de relacion bidireccional para evitar que un ciclo infinito rompa el JSON
    // El handler y el hibernateLazyInitializer también es bueno colocarlo para prevenir un error de memoria que lo que hace es generar ese mismo ciclo infinito (mas en casos de ManyToMany o OneToMany independientemente si es bidireccional o no)
    @JsonIgnoreProperties({"role", "handler", "hibernateLazyInitializer"})
    @ManyToMany(mappedBy = "role")
    private List<User> user;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
        this.user = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
    // El equals y hashCode se usan para mantener la integridad de los datos y evitar objetos repetidos o duplicados. Es obligatorio en las dos entidades cuando la relacion sea bidireccional
    // En casos donde la relacion sea bidireccional se debe generar el equal y hashCode de solo los atributos unicos (en esta entidad seria el ID y el name)
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Role role = (Role) object;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
