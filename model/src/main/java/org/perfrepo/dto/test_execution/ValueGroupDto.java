package org.perfrepo.dto.test_execution;

import java.util.List;

/**
 * @author Jiri Grunwald (grunwjir@gmail.com)
 */
public class ValueGroupDto {

    private Long metricId;

    private List<ValueDto> values;

    public Long getMetricId() {
        return metricId;
    }

    public void setMetricId(Long metricId) {
        this.metricId = metricId;
    }

    public List<ValueDto> getValues() {
        return values;
    }

    public void setValues(List<ValueDto> values) {
        this.values = values;
    }
}