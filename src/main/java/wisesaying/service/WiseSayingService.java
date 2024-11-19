package wisesaying.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import infrastructure.wisesaying.WiseSayingEntity;
import wisesaying.domain.WiseSaying;
import wisesaying.exception.WiseSayingException;
import infrastructure.wisesaying.WiseSayingRepository;

public class WiseSayingService {
	private static final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

	public Long add(String wiseSaying, String writer) {
		Long savedId = 0L;
		try {
			WiseSaying createWiseSaying = WiseSaying.createWiseSaying(wiseSaying, writer);
			savedId = wiseSayingRepository.add(createWiseSaying);

			return savedId;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return savedId;
	}

	public void findAll() {
		try {
			LinkedList<WiseSayingEntity> findAll = wiseSayingRepository.findAll();
			System.out.println("번호 / 작가 / 명언");
			for (WiseSayingEntity wiseSayingEntity : findAll) {
				System.out.printf("%d / %s / %s \n",
					wiseSayingEntity.getId(), wiseSayingEntity.getWriter(), wiseSayingEntity.getWiseSaying());
			}
		} catch (IOException e) {
			e.printStackTrace();
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

	public void update(Scanner sc) {
		try {
			System.out.print("수정?id = ");
			Long target = sc.nextLong();
			sc.nextLine();

			WiseSayingEntity targetEntity = wiseSayingRepository.findById(target);

			System.out.println("명언 (기존) : " + targetEntity.getWiseSaying());
			System.out.print("명언 : ");
			String updateWiseSaying = sc.nextLine();

			System.out.println("작가 (기존) : " + targetEntity.getWriter());
			System.out.print("작가 : ");
			String updateWriter = sc.nextLine();

			if (!updateWiseSaying.equals(targetEntity.getWiseSaying())) {
				targetEntity.setWiseSaying(updateWiseSaying);
			}

			if (!updateWriter.equals(targetEntity.getWriter())) {
				targetEntity.setWriter(updateWriter);
			}

			wiseSayingRepository.update(targetEntity);
		} catch (WiseSayingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void build() {
		try {
			wiseSayingRepository.build();
			System.out.println("data.json 파일의 내용이 갱신되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}