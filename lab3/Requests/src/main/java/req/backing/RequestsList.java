/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package req.backing;

import data.RequestRepository;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import req.entities.Request;
import jakarta.faces.component.html.HtmlDataTable;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Julia Lamperska
 */
@Named(value = "requestsList")
@RequestScoped
public class RequestsList {

    @Inject
    private RequestRepository requestRepository;
    
    /**
     * Creates a new instance of RequestsList
     */
    public RequestsList() {
    }
    
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Size(min = 3, max = 60, message="{request.size}")
    private String newRequest;

    /**
     * Get the value of newRequest
     *
     * @return the value of newRequest
     */
    public String getNewRequest() {
        return newRequest;
    }

    /**
     * Set the value of newRequest
     *
     * @param newRequest new value of newRequest
     */
    public void setNewRequest(String newRequest) {
        this.newRequest = newRequest;
    }

    private HtmlDataTable requestsDataTable;

    /**
     * Get the value of requestsDataTable
     *
     * @return the value of requestsDataTable
     */
    public HtmlDataTable getRequestsDataTable() {
        return requestsDataTable;
    }

    /**
     * Set the value of requestsDataTable
     *
     * @param requestsDataTable new value of requestsDataTable
     */
    public void setRequestsDataTable(HtmlDataTable requestsDataTable) {
        this.requestsDataTable = requestsDataTable;
    }
    
    @Transactional
    public String addRequest() {
        Request request = new Request();
        request.setRequestText(this.getNewRequest());
        request.setRequestDate(LocalDate.now());
        requestRepository.create(request);
        setNewRequest("");
        return null;
    }

    @Transactional
    public String deleteRequest() {
        Request req = (Request) this.getRequestsDataTable().getRowData();
        requestRepository.remove(req);
        return null;
    }
    
}
