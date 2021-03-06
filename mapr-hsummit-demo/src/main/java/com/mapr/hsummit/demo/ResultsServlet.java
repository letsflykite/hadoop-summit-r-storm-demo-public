package com.mapr.hsummit.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mapr.hsummit.demo.model.DataPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

public class ResultsServlet extends HttpServlet {
    static int DATA_POINT_LIMIT = 5000;
    ObjectMapper mapper = new ObjectMapper();

    public ResultsServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LinkedList<DataPoint> dataPoints = null;
        if (request.getPathInfo().contains("/points")) {
            dataPoints = DataHolder.dataSeries.get(DataHolder.DataPointSeries.SCORE);
        } else if (request.getPathInfo().equals("/levels")) {
            dataPoints = DataHolder.dataSeries.get(DataHolder.DataPointSeries.SCORE);
        } else {
            System.out.println("kkkk " + request.getPathInfo());
            dataPoints = Lists.newLinkedList();
        }

        System.out.println("data called " + dataPoints.size());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(mapper.writeValueAsString(dataPoints));
    }
}