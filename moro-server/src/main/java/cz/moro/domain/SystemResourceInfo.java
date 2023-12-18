package cz.moro.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class SystemResourceInfo {

    @Id
    @Column("id")
    private Long id;

    @Column("client_name")
    private String clientName;

    @Column("cpu_usage")
    private double cpuUsage;

    @Column("memory_usage")
    private long memoryUsage; // in bytes

    @Column("operating_system")
    private String operatingSystem;

    @Column("total_memory")
    private long totalMemory; // in bytes

    @Column("free_memory")
    private long freeMemory; // in bytes

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

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public long getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(long memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    @Override
    public String toString() {
        return String.format(
                "SystemResourceInfo{id=%d, clientName='%s', cpuUsage=%.2f, memoryUsage=%d, operatingSystem='%s', totalMemory=%d, freeMemory=%d}",
                id, clientName, cpuUsage, memoryUsage, operatingSystem, totalMemory, freeMemory);
    }

}
