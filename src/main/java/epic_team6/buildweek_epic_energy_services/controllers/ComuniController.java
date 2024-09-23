package epic_team6.buildweek_epic_energy_services.controllers;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import epic_team6.buildweek_epic_energy_services.entities.Comune;
import epic_team6.buildweek_epic_energy_services.entities.Provincia;
import epic_team6.buildweek_epic_energy_services.services.ComuniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comuni")
public class ComuniController {
    @Autowired
    private ComuniService comuniService;

    @PostMapping("/upload")
    public String uploadData(@RequestParam("file") MultipartFile file) throws Exception{
        List<Comune> comuni = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings =new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.getFormat().setDelimiter(';');

        CsvParser parser = new CsvParser(settings);

        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            Comune comune = new Comune();
            comune.setCodiceProvincia(record.getString("Codice Provincia (Storico)(1)"));
            comune.setCodiceProgressivo(record.getString("Progressivo del Comune (2)" ));
            comune.setNome(record.getString("Denominazione in italiano"));

            comuni.add(comune);

        });
        comuniService.saveAll(comuni);
        return "Upload dei comuni riuscito";
    }
}
