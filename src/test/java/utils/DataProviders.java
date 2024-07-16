package utils;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.List;

public class DataProviders {

   // data provider for pet status : available, pending, sold
    @DataProvider(name = "petStatus")
    public Object[] petStatus() {
        return new Object[]{"available", "pending", "sold"};
    }

    // data provider for pet id
    @DataProvider(name = "petId")
    public Object[] petId() {
        return new Object[]{7777777, 8888888, 9999999};
    }


}
