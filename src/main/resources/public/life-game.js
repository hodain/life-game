angular.module('life-game', [])
	.controller('LifeGameController', function(CellGridService) {
		var lifeGame = this;

		lifeGame.cellGrid = [ [ false, false ][false, false] ];

		lifeGame.setState = function(x, y) {
			var state = !this.cellGrid[x][y];
			this.cellGrid[x][y] = state;
			CellGridService.setCell(x, y, state)
		};

		lifeGame.init = function() {
			var data = CellGridService.getCellGrid();
			data.then(function(data) {
				lifeGame.cellGrid = data;
			});
		};

		lifeGame.clear = function() {
			lifeGame.cellGrid = [ [ false, false ][false, false] ];
		};

		lifeGame.next = function() {
			var data = CellGridService.next();
			data.then(function(data) {
				lifeGame.cellGrid = data;
			});
		};
		
		lifeGame.add = function() {
			CellGridService.add();
			lifeGame.init();
		};
	})
	.factory("CellGridService", [ '$http', function($http) {
		var getCellGrid = function() {
			return $http.get('http://localhost:8080/matrix').then(function(response) {
				return response.data;
			});
		};
		var next = function() {
			return $http.get('http://localhost:8080/next').then(function(response) {
				return response.data;
			});
		};
		var setCell = function(x, y, state) {
			var data = {
				x : x,
				y : y,
				state : state
			};
			$http.post('http://localhost:8080/cell', data);
		};
		var add = function() {
			$http.post('http://localhost:8080/add');
		};
		
		return {
			getCellGrid : getCellGrid,
			next : next,
			setCell : setCell,
			add : add
		};
	} ]);