package com.master.app.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank
    @Size(min = 4, max = 12)
    private String username;
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    // Esto se usa en casos de relacion bidireccional para evitar que un ciclo infinito rompa el JSON
    // El handler y el hibernateLazyInitializer también es bueno colocarlo para prevenir un error de memoria que lo que hace es generar ese mismo ciclo infinito (mas en casos de ManyToMany o OneToMany independientemente si es bidireccional o no)
    @JsonIgnoreProperties({"user", "handler", "hibernateLazyInitializer"})
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
    )
    private List<Role> role;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean enabled;
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;
    // Util para prevenir que cualquier modificacion en el Json se registre en BD, lo cual hace que los datos sean persistentes
    @PrePersist
    private void prePersist(){
        enabled = true;
    }

    public User() {
        role = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(min = 4, max = 12) String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Size(min = 4, max = 12) String username) {
        this.username = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    // El equals y hashCode se usan para mantener la integridad de los datos y evitar objetos repetidos o duplicados. Es obligatorio en las dos entidades cuando la relacion sea bidireccional
    // En casos donde la relacion sea bidireccional se debe generar el equal y hashCode de solo los atributos unicos (en esta entidad seria el ID y el username)
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
