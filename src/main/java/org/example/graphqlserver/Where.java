package org.example.graphqlserver;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Where {
    private Filter filter;
    private List<Where> and;
    private List<Where> or;
    private Where not;
}
