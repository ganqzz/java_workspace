package hbdemo.entities;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TIME_TEST")
public class TimeTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIME_TEST_ID")
    private Long timeTestId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATETIME_COLUMN")
    private Date datetimeColumn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP_COLUMN")
    private Date timestampColumn;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_COLUMN")
    private Date dateColumn;

    @Temporal(TemporalType.TIME)
    @Column(name = "TIME_COLUMN")
    private Date timeColumn;

    @Column(name = "SQL_DATETIME_COLUMN")
    private Timestamp sqlDatetimeColumn;

    @Column(name = "SQL_TIMESTAMP_COLUMN")
    private Timestamp sqlTimestampColumn;

    @Column(name = "SQL_DATE_COLUMN")
    private java.sql.Date sqlDateColumn;

    @Column(name = "SQL_TIME_COLUMN")
    private Time sqlTimeColumn;

    public TimeTest() {}

    public TimeTest(Date date) {
        this.datetimeColumn = date;
        this.timestampColumn = date;
        this.timeColumn = date;
        this.dateColumn = date;

        this.sqlDatetimeColumn = new Timestamp(date.getTime());
        this.sqlTimestampColumn = new Timestamp(date.getTime());
        this.sqlDateColumn = new java.sql.Date(date.getTime());
        this.sqlTimeColumn = new Time(date.getTime());
    }

    public Long getTimeTestId() {
        return timeTestId;
    }

    public void setTimeTestId(Long timeTestId) {
        this.timeTestId = timeTestId;
    }

    public Date getDatetimeColumn() {
        return datetimeColumn;
    }

    public void setDatetimeColumn(Date datetimeColumn) {
        this.datetimeColumn = datetimeColumn;
    }

    public Date getTimestampColumn() {
        return timestampColumn;
    }

    public void setTimestampColumn(Date timestampColumn) {
        this.timestampColumn = timestampColumn;
    }

    public Date getDateColumn() {
        return dateColumn;
    }

    public void setDateColumn(Date dateColumn) {
        this.dateColumn = dateColumn;
    }

    public Date getTimeColumn() {
        return timeColumn;
    }

    public void setTimeColumn(Date timeColumn) {
        this.timeColumn = timeColumn;
    }

    public Timestamp getSqlDatetimeColumn() {
        return sqlDatetimeColumn;
    }

    public void setSqlDatetimeColumn(Timestamp sqlDatetimeColumn) {
        this.sqlDatetimeColumn = sqlDatetimeColumn;
    }

    public Timestamp getSqlTimestampColumn() {
        return sqlTimestampColumn;
    }

    public void setSqlTimestampColumn(Timestamp sqlTimestampColumn) {
        this.sqlTimestampColumn = sqlTimestampColumn;
    }

    public java.sql.Date getSqlDateColumn() {
        return sqlDateColumn;
    }

    public void setSqlDateColumn(java.sql.Date sqlDateColumn) {
        this.sqlDateColumn = sqlDateColumn;
    }

    public Time getSqlTimeColumn() {
        return sqlTimeColumn;
    }

    public void setSqlTimeColumn(Time sqlTimeColumn) {
        this.sqlTimeColumn = sqlTimeColumn;
    }

    @Override
    public String toString() {
        return "TimeTest ["
               + "\n  timeTestId=" + timeTestId
               + "\n  datetimeColumn=" + datetimeColumn
               + "\n  timestampColumn=" + timestampColumn
               + "\n  dateColumn=" + dateColumn
               + "\n  timeColumn=" + timeColumn
               + "\n  sqlDatetimeColumn=" + sqlDatetimeColumn
               + "\n  sqlTimestampColumn=" + sqlTimestampColumn
               + "\n  sqlDateColumn=" + sqlDateColumn
               + "\n  sqlTimeColumn=" + sqlTimeColumn
               + "\n]";
    }
}
