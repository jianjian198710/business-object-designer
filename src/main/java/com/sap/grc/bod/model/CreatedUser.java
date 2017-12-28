package com.sap.grc.bod.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public @Data class CreatedUser implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1612724273216693017L;

    @Column( name = "created_user_name" )
    private String name;

    @Column( name = "created_user_mail" )
    private String mail;
}
