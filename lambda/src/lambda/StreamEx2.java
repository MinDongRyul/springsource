package lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamEx2 {
	public static void main(String[] args) {
		//디렉토리 지정
		Path path = Paths.get("c:\\upload\\2022\\05\\10");

		//stream 생성
		try {
			Stream<Path> stream = Files.list(path);
			stream.forEach(file -> System.out.println(file.getFileName()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
