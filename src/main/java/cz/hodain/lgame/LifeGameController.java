package cz.hodain.lgame;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LifeGameController {

	private boolean[][] matrix = {	{true, false, false, false, false, true},
									{false, true, false, false, true, false},
									{false, false, true, true, false, false},
									{false, false, true, true, false, false},
									{false, true, false, false, true, false},
									{true, false, false, false, false, true}};

	private CellGrid cellGrid = new CellGrid(matrix);

	private static final Logger LOG = LoggerFactory.getLogger(LifeGameController.class);


	@RequestMapping("/matrix")
	public boolean[][] getMatrix() {
		LOG.info("MATRIX was called.");
		boolean[][] result = cellGrid.getMatrix();
		LOG.info(cellGrid.toString());
		return result;
	}


	@RequestMapping(method = RequestMethod.POST, value = "/cell")
	public void setCell(@RequestBody Map<String, Object> data) {
		LOG.info("MATRIX was called.");
		cellGrid.getCell((Integer) data.get("x"), (Integer) data.get("y")).setLife((Boolean) data.get("state"));
		LOG.info(cellGrid.toString());
	}


	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public void add() {
		LOG.info("ADD was called.");
		cellGrid.addRow();
		cellGrid.addColumn();
		LOG.info(cellGrid.toString());
	}


	@RequestMapping("/next")
	public boolean[][] getNext() {
		LOG.info("NEXT was called.");
		cellGrid.next();
		boolean[][] result = cellGrid.getMatrix();
		LOG.info(cellGrid.toString());
		return result;
	}


	@RequestMapping(method = RequestMethod.POST, value = "/init")
	public boolean[][] init(@RequestBody boolean[][] matrix) {
		LOG.info("INIT was called.");
		this.cellGrid = new CellGrid(matrix);
		boolean[][] result = this.cellGrid.getMatrix();
		LOG.info(cellGrid.toString());
		return result;
	}

}
