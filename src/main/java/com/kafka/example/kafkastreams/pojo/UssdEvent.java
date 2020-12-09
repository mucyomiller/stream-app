package com.kafka.example.kafkastreams.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UssdEvent implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty
    public String step;

    @JsonProperty
    public String loglevel;

    @JsonProperty
    public String traceid;

    @JsonProperty
    public String clientid;

    @JsonProperty
    public String Message;

    @JsonProperty
    public String Application;

    @JsonProperty
    public String Time;

    @JsonProperty
    public String appKind;

    @JsonProperty
    public String action;

    public UssdEvent() {
    }

    @JsonCreator
    public UssdEvent(@JsonProperty("step") String step, @JsonProperty("loglevel") String loglevel,
            @JsonProperty("traceid") String traceid, @JsonProperty("clientid") String clientid,
            @JsonProperty("Message") String Message, @JsonProperty("Application") String Application,
            @JsonProperty("Time") String Time, @JsonProperty("appKind") String appKind,
            @JsonProperty("action") String action) {
        this.step = step;
        this.loglevel = loglevel;
        this.traceid = traceid;
        this.clientid = clientid;
        this.Message = Message;
        this.Application = Application;
        this.Time = Time;
        this.appKind = appKind;
        this.action = action;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getLoglevel() {
        return loglevel;
    }

    public void setLoglevel(String loglevel) {
        this.loglevel = loglevel;
    }

    public String getTraceid() {
        return traceid;
    }

    public void setTraceid(String traceid) {
        this.traceid = traceid;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getApplication() {
        return Application;
    }

    public void setApplication(String application) {
        Application = application;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAppKind() {
        return appKind;
    }

    public void setAppKind(String appKind) {
        this.appKind = appKind;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}