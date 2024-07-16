package api.endpoints;

/* This class will contain the routes for the API endpoints. */
/*
        Swagger URI: https://petstore.swagger.io/
        Base URI: https://petstore.swagger.io/v2
        Endpoints:
        1. POST : /pet
        2. GET : /pet/{petId}
        3. PUT : /pet
        4. DELETE : /pet/{petId}
        5. GET : /pet/findByStatus?status=available
        6. POST : /pet/{petId}/uploadImage
        7. POST : /store/order
*/
public class Routes {
    public static final String BASE_URI = "https://petstore.swagger.io/v2";
    public static final String PET = "/pet";
    public static final String PET_ID = "/pet/{petId}";
    public static final String PET_STATUS = "/pet/findByStatus?status={status}";
    public static final String PET_UPLOAD_IMAGE = "/pet/{petId}/uploadImage";
    public static final String STORE_ORDER = "/store/order";
}