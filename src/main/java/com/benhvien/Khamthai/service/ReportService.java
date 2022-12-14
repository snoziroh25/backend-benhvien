package com.benhvien.Khamthai.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.model.AccountModel;
import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.model.TreSoSinhModel;
import com.benhvien.Khamthai.repository.AccountRepositoty;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;
import com.benhvien.Khamthai.repository.HoSoKhamRepository;
import com.benhvien.Khamthai.repository.HoSoKhamTreRepository;
import com.benhvien.Khamthai.repository.LichSuTiemRepository;
import com.benhvien.Khamthai.repository.TreSoSinhRepository;

@Service
public class ReportService {

	@Autowired
	AccountRepositoty accountRepositoty;

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	@Autowired
	TreSoSinhRepository treSoSinhRepository;

	@Autowired
	LichSuTiemRepository lichSuTiemRepository;

	@Autowired
	HoSoKhamTreRepository hoSoKhamTreRepository;

	@Autowired
	HoSoKhamRepository hoSoKhamRepository;

	public Integer getSum(List<Integer> list) {
		Integer sum = 0;
		for (Integer i : list) {
			sum += i;
		}
		return sum;
	}

	public Object getReportByUsername(String username, String year) {
		Map<String, Object> res = new HashMap<>();
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Account Id not found!");
		}
		List<CoSoYTeModel> listCSYT = coSoYTeRepository.findBySoYTeId(opAccount.get().getInfoId());
		List<String> listName = new ArrayList<>();
		List<List<Integer>> listCount = new ArrayList<>();
		Integer[] listCountByMonth = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int countTiem = 0;
		int countKhamTre = 0;
		int countKham = 0;
		for (CoSoYTeModel csyt : listCSYT) {
			listName.add(csyt.getTen());
			List<Integer> count = new ArrayList<>();
			for (int i = 1; i < 13; i++) {
				String timeStart = "";
				String timeEnd = "";
				if (i < 9) {
					timeStart = year + "-0" + String.valueOf(i) + "-01";
					timeEnd = year + "-0" + String.valueOf(i + 1) + "-01";
				} else if (i == 9) {
					timeStart = year + "-0" + String.valueOf(i) + "-01";
					timeEnd = year + "-" + String.valueOf(i + 1) + "-01";
				} else if (i < 12) {
					timeStart = year + "-" + String.valueOf(i) + "-01";
					timeEnd = year + "-" + String.valueOf(i + 1) + "-01";
				} else if (i == 12) {
					timeStart = year + "-" + String.valueOf(i) + "-01";
					timeEnd = String.valueOf(Integer.valueOf(year) + 1) + "-01-01";
				}
				List<TreSoSinhModel> listTre = treSoSinhRepository.findByCSYTAndDate(timeStart, timeEnd, csyt.getId());
				count.add(listTre.size());
				listCountByMonth[i - 1] += listTre.size();
			}
			countTiem += lichSuTiemRepository.findByCSYTAndDate(csyt.getId(), year + "-01-01", year + "12-31").size();
			countKhamTre += hoSoKhamTreRepository.findByCSYTAndDate(year + "-01-01", year + "12-31", csyt.getId())
					.size();
			countKham += hoSoKhamRepository.findByCSYTAndDate(year + "-01-01", year + "12-31", csyt.getId()).size();
			count.add(getSum(count));
			listCount.add(count);
		}

		res.put("totalKham", countKham);
		res.put("totalKhamTre", countKhamTre);
		res.put("totalTiem", countTiem);
		res.put("countByMonth", listCountByMonth);
		res.put("count", listCount);
		res.put("name", listName);
		return res;
	}

	public ByteArrayInputStream exportExcel(String username, String year) throws Exception {

		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Account Id not found!");
		}
		List<CoSoYTeModel> listCSYT = coSoYTeRepository.findBySoYTeId(opAccount.get().getInfoId());
		List<String> listName = new ArrayList<>();
		List<List<Integer>> listCount = new ArrayList<>();
		Integer[] listCountByMonth = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int countTiem = 0;
		int countKhamTre = 0;
		int countKham = 0;
		for (CoSoYTeModel csyt : listCSYT) {
			listName.add(csyt.getTen());
			List<Integer> count = new ArrayList<>();
			for (int i = 1; i < 13; i++) {
				String timeStart = "";
				String timeEnd = "";
				if (i < 9) {
					timeStart = year + "-0" + String.valueOf(i) + "-01";
					timeEnd = year + "-0" + String.valueOf(i + 1) + "-01";
				} else if (i == 9) {
					timeStart = year + "-0" + String.valueOf(i) + "-01";
					timeEnd = year + "-" + String.valueOf(i + 1) + "-01";
				} else if (i < 12) {
					timeStart = year + "-" + String.valueOf(i) + "-01";
					timeEnd = year + "-" + String.valueOf(i + 1) + "-01";
				} else if (i == 12) {
					timeStart = year + "-" + String.valueOf(i) + "-01";
					timeEnd = String.valueOf(Integer.valueOf(year) + 1) + "-01-01";
				}
				List<TreSoSinhModel> listTre = treSoSinhRepository.findByCSYTAndDate(timeStart, timeEnd, csyt.getId());
				count.add(listTre.size());
				listCountByMonth[i - 1] += listTre.size();
			}
			countTiem += lichSuTiemRepository.findByCSYTAndDate(csyt.getId(), year + "-01-01", year + "12-31").size();
			countKhamTre += hoSoKhamTreRepository.findByCSYTAndDate(year + "-01-01", year + "12-31", csyt.getId())
					.size();
			countKham += hoSoKhamRepository.findByCSYTAndDate(year + "-01-01", year + "12-31", csyt.getId()).size();
			count.add(getSum(count));
			listCount.add(count);
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet(" Th???ng k?? n??m " + year);
		XSSFRow row;
		Map<String, Object[]> data = new TreeMap<String, Object[]>();

		data.put("1", new Object[] { "C?? s??? y t???", "Th??ng 1", "Th??ng 2", "Th??ng 3", "Th??ng 4", "Th??ng 5", "Th??ng 6",
				"Th??ng 7", "Th??ng 8", "Th??ng 9", "Th??ng 10", "Th??ng 11", "Th??ng 12", "T???ng" });

		for (int i = 0; i < listName.size(); i++) {
			for (int j = 0; j < 13; j++) {
				data.put(String.valueOf(i + 2),
						new Object[] { listName.get(i), String.valueOf(listCount.get(i).get(0)),
								String.valueOf(listCount.get(i).get(1)), String.valueOf(listCount.get(i).get(2)),
								String.valueOf(listCount.get(i).get(3)), String.valueOf(listCount.get(i).get(4)),
								String.valueOf(listCount.get(i).get(5)), String.valueOf(listCount.get(i).get(6)),
								String.valueOf(listCount.get(i).get(7)), String.valueOf(listCount.get(i).get(8)),
								String.valueOf(listCount.get(i).get(9)), String.valueOf(listCount.get(i).get(10)),
								String.valueOf(listCount.get(i).get(11)), String.valueOf(listCount.get(i).get(12)) });
			}
		}

		data.put(String.valueOf(listName.size() + 2),
				new Object[] { "T???ng", String.valueOf(listCountByMonth[0]), String.valueOf(listCountByMonth[1]),
						String.valueOf(listCountByMonth[2]), String.valueOf(listCountByMonth[3]),
						String.valueOf(listCountByMonth[4]), String.valueOf(listCountByMonth[5]),
						String.valueOf(listCountByMonth[6]), String.valueOf(listCountByMonth[7]),
						String.valueOf(listCountByMonth[8]), String.valueOf(listCountByMonth[9]),
						String.valueOf(listCountByMonth[10]), String.valueOf(listCountByMonth[11]) });

		data.put(String.valueOf(listName.size() + 4),
				new Object[] { "S??? l?????t kh??m c???a thai ph??? trong n??m:", String.valueOf(countKham) });
		data.put(String.valueOf(listName.size() + 5),
				new Object[] { "S??? l?????t kh??m c???a tr??? s?? sinh trong n??m:", String.valueOf(countKhamTre) });
		data.put(String.valueOf(listName.size() + 6),
				new Object[] { "S??? l?????t ti??m trong n??m:", String.valueOf(countTiem) });

		Set<String> keyid = data.keySet();

		int rowid = 0;

		for (String key : keyid) {

			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = data.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}

		spreadsheet.autoSizeColumn(0);

		workbook.write(out);
		workbook.close();

		return new ByteArrayInputStream(out.toByteArray());
	}
}
