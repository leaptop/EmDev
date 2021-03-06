import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class WorkWithTXT {
    static String showANumberOfEachLetter(String str) {
        char[] car = str.toCharArray();
        ArrayList<Character> cararr = new ArrayList<Character>();
        for (int i = 0; i < car.length; i++) {
            cararr.add(car[i]);
        }
        Map<Character, Integer> mapp = new TreeMap<>();
        for (int i = 0; i < car.length; i++) {
            char ch = car[i];
            int num = 0;
            for (int f = 0; f < car.length; f++) {
                if (car[f] == ch) {
                    num++;
                }
            }
            mapp.putIfAbsent(ch, num);

        }
/*        mapp.entrySet()
                .stream()
                . sorted(Map.Entry.comparingByValue())

                .forEach(System.out::println);*/
        return entriesSortedByValues(mapp).toString();
        //System.out.println(entriesSortedByValues(mapp));
    }
    static <K,V extends Comparable<? super V>>
    List<Map.Entry<K, V>> entriesSortedByValues(Map<K,V> map) {

        List<Map.Entry<K,V>> sortedEntries = new ArrayList<Map.Entry<K,V>>(map.entrySet());

        Collections.sort(sortedEntries,
                new Comparator<Map.Entry<K,V>>() {
                    @Override
                    public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                }
        );

        return sortedEntries;
    }

    public static String showAnumberOfWordsInText(String str) {
        String[] lstr = str.split("[\\s., ]+");
        return "A number of words in the text is : " + lstr.length;
    }
    public static String getAstringFromTXT() throws IOException {
       // String fileName = "C:\\Users\\stepa\\EmDev\\Task1_00\\src\\input.txt";
        String fileName = "IN/input.txt";

        return Files.readString(Paths.get(fileName));
    }

    public static void main0(String[] args) throws IOException {

      // showANumberOfEachLetter(getAstringFromTXT());
        writeTextToFile(showANumberOfEachLetter(getAstringFromTXT()));

    }
    public static void writeTextToFile(String s) throws IOException {
        Files.writeString(Paths.get("out/out.txt"), s);
    }

}

/*
Задание:

В файле input.txt располагается некоторый текст. Необходимо вывести в System.out количество
вхождений каждой буквы в отсортированном по убыванию виде.

Дополнительные задания:

Вывести количество слов в тексте
Реализовать вывод в файл
Отслеживать появление текстовых файлов в папке IN, запускать по ним вычисления и складывать результаты в папочку OUT

Задание для Junior (Liferay)
Список пользователей портала.
Нужно разработать простой MVC портлет для отображения списка пользователей, которые есть на портале.
Поля для вывода: userId, ФИО, email, должность, день рождения (формат "dd-MM-yyyy г.").
Для работы с пользователями нужно использовать сервис UserLocalService - пример использования можно
посмотреть тут https://github.com/liferay/liferay-blade-samples/blob/7.2/liferay-workspace/apps/rest/src/main/java/com/liferay/blade/samples/rest/UsersRestService.java#L79
Пример использования сервиса при рендере портлета можно посмотреть тут
https://github.com/liferay/liferay-blade-samples/blob/7.2/liferay-workspace/apps/service-builder/basic/basic-web.

Ссылка для скачивания портала:
https://sourceforge.net/projects/lportal/files/Liferay%20Portal/7.2.0%20GA1/liferay-ce-portal-tomcat-7.2.0-ga1-20190531153709761.7z/download
Документация для создания простого MVC портлета:
https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet
\\Примеры кода можно посмотреть тут: https://github.com/liferay/liferay-blade-samples/tree/7.2/liferay-workspace
Для текущей задачи лучше подойдет вот этот пример:
https://github.com/liferay/liferay-blade-samples/tree/7.2/liferay-workspace/apps/render-command-portlet

Дополнительные задания:
1. На jsp использовать для вывода теглибу "liferay-search-container" -
пример можно посмотреть тут - http://www.javasavvy.com/liferay-search-container-example.
Добавить поле "Номера телефонов" - список телефонов которые есть у пользователя.
3. Добавить поле "Организации" - названия организаций в которые входит пользователь.
4. Нужно отображать только тех пользователей, которые доступны текущему пользователю для просмотра.
5. В списке пользователей нужно выводить только ФИО в виде ссылки. Ссылка должна открывать подробный
просмотр всех полей выбранного пользователя на отдельной jsp.
(Пример: https://github.com/liferay/liferay-blade-samples/blob/7.2/liferay-workspace/apps/service-builder/basic/basic-web/src/main/resources/META-INF/resources/view.jsp)

6. День рождения выводить в русском формате со склонением месяцев, например "1 января".
 */