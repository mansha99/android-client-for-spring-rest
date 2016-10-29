package mansha99.com.restclient.dto;

import java.util.List;

import mansha99.com.restclient.models.Contact;
import mansha99.com.restclient.models.SortInfo;

/**
 * Created by shivay on 10/28/2016.
 */
public class ContactListDTO {
    private List<Contact> content;
    private String totalPages;// 1,
    private String totalElements;// 7,
    private String last;// true,
    private String size;// 20,
    private String number;// 0,
    private String first;// true,
    private String numberOfElements;// 7,
    private List<SortInfo> sort;// null


    public List<Contact> getContent() {
        return content;
    }

    public void setContent(List<Contact> content) {
        this.content = content;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(String totalElements) {
        this.totalElements = totalElements;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(String numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<SortInfo> getSort() {
        return sort;
    }

    public void setSort(List<SortInfo> sort) {
        this.sort = sort;
    }
}

