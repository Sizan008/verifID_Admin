package com.leads.microcube.verifidadmin.ecverification.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EcAddressOptionResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}