package org.example.graphqlserver;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Filter {
    private String attributeId;
    private String operator;
    private String attributeValue;
}
