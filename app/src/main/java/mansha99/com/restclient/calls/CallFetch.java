package mansha99.com.restclient.calls;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import mansha99.com.restclient.dto.ContactListDTO;
import mansha99.com.restclient.utils.AppHelper;
import mansha99.com.restclient.utils.WebClient;

public class CallFetch {
    public static ContactListDTO get(Integer page, Integer size, String sort, String dir) throws Exception {
        ContactListDTO dto = null;
        ContactCallable callable1 = new ContactCallable(page, size, sort, dir);
        FutureTask<ContactListDTO> futureTask = new FutureTask<ContactListDTO>(callable1);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask);
        while (true) {
            if (futureTask.isDone()) {
                dto = futureTask.get();
                executor.shutdown();
                break;
            }
        }
        return dto;
    }

    static class ContactCallable implements Callable<ContactListDTO> {

        Integer page;
        Integer size;
        String sort;
        String dir;


        public ContactCallable(Integer page, Integer size, String sort, String dir) {
            this.page = page;
            this.size = size;
            this.sort = sort;
            this.dir = dir;
        }

        @Override
        public ContactListDTO call() throws Exception {
            String jsonString = WebClient.FetchContacts(page, size, sort, dir);
            AppHelper.LogCat("[JSON]  : " + jsonString);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonString, ContactListDTO.class);
        }
    }
}