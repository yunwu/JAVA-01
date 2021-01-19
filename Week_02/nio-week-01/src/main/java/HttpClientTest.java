import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author wangdan
 * @date 2021/1/19
 */
public class HttpClientTest {

    private static final String URL = "http://localhost:8801";
    public static void main(String[] args){
        sendGet(URL);
    }

    public static void sendGet(String url){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(url).get();

        try (Response response = mOkHttpClient.newCall(requestBuilder.build()).execute()) {
            String result = response.body().string();
            System.out.println("访问结果" + result);

        } catch (Exception e) {
            System.out.println("出现异常," + e.getMessage());
        }
    }
}
