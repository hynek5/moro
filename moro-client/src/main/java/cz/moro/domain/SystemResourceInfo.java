package cz.moro.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemResourceInfo {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("clientName")
    private String clientName;

    @JsonProperty("cpuUsage")
    private Double cpuUsage;

    @JsonProperty("memoryUsage")
    private Long memoryUsage;

    @JsonProperty("operatingSystem")
    private String operatingSystem;

    @JsonProperty("totalMemory")
    private Long totalMemory;

    @JsonProperty("freeMemory")
    private Long freeMemory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Long getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(Long memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(Long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public Long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(Long freeMemory) {
        this.freeMemory = freeMemory;
    }
}
