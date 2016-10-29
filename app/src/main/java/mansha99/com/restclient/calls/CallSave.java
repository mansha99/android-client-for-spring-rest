package mansha99.com.restclient.calls;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import mansha99.com.restclient.dto.SaveContactDTO;
import mansha99.com.restclient.models.Contact;
import mansha99.com.restclient.utils.AppHelper;
import mansha99.com.restclient.utils.WebClient;

public class CallSave {
    public static SaveContactDTO get(Contact model) throws Exception {
        SaveContactDTO dto = null;
        SaveCallable callable1 = new SaveCallable(model);
        FutureTask<SaveContactDTO> futureTask = new FutureTask<SaveContactDTO>(callable1);
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

    static class SaveCallable implements Callable<SaveContactDTO> {

        Contact model;


        public SaveCallable(Contact model) {
            this.model=model;
        }

        @Override
        public SaveContactDTO call() throws Exception {
            String jsonString = WebClient.SaveContact(model);
            AppHelper.LogCat("[JSON]  : " + jsonString);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonString, SaveContactDTO.class);
        }
    }
}