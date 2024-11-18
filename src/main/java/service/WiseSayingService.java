package service;

import java.util.LinkedList;
import java.util.Scanner;

import entity.WiseSayingEntity;
import exception.WiseSayingException;
import repository.WiseSayingRepository;

public class WiseSayingService {
	private static final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

	public void add(Scanner sc) {
		System.out.print("명언 : ");
		String wiseSaying = sc.nextLine();
		System.out.print("작가 : ");
		String writer = sc.nextLine();
		WiseSayingEntity wiseSayingEntity = new WiseSayingEntity(wiseSaying, writer);
		Long savedId = wiseSayingRepository.add(wiseSayingEntity);
		System.out.println(savedId + "번 명언이 등록되었습니다.");
	}

	public void findAll() {
		LinkedList<WiseSayingEntity> all = wiseSayingRepository.findAll();
		System.out.println("번호 / 작가 / 명언");
		for (WiseSayingEntity wiseSayingEntity : all) {
			System.out.printf("%d / %s / %s \n",
				wiseSayingEntity.getId(), wiseSayingEntity.getWriter(), wiseSayingEntity.getWiseSaying());
		}
	}

	public void delete(Scanner sc) {
		try {
			System.out.print("삭제?id = ");

			Long targetId = sc.nextLong();
			sc.nextLine();
			Long deletedId = wiseSayingRepository.delete(targetId);

			System.out.println(deletedId + "번 명언이 삭제되었습니다.");
		} catch (WiseSayingException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("숫자만 입력하세요.");
		}
	}
}
