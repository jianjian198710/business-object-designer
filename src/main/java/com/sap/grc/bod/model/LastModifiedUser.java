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
public @Data class LastModifiedUser implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 5331102238224077388L;

    @Column( name = "last_modified_user_name" )
    private String name;

    @Column( name = "last_modified_user_mail" )
    private String mail;

}
