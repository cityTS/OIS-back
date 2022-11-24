package cn.edu.gxu.oisback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private Integer total;
    private Object data;
}
