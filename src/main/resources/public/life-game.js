angular.module('life-game', [])
	.controller('LifeGameController', function(CellGridService, $interval, $scope) {
		var lifeGame = this;
		var stop;

		lifeGame.cellGrid;

		lifeGame.setState = function(x, y) {
			var state = !this.cellGrid[x][y];
			this.cellGrid[x][y] = state;
			CellGridService.setCell(x, y, state)
		};

		lifeGame.init = function() {
			getData(CellGridService.getCellGrid());
		};

		lifeGame.clear = function() {
			getData(CellGridService.clear());
		};

		lifeGame.next = function() {
			getData(CellGridService.next());
		};
		
		lifeGame.add = function() {
			CellGridService.add();
			lifeGame.init();
		};

		lifeGame.remove = function() {
			CellGridService.remove();
			lifeGame.init();
		};
		
		lifeGame.play = function() {
			stop = $interval(function(){lifeGame.next()}, 500);
			$scope.running = true;
		};
		
		lifeGame.stop = function() {
			if (angular.isDefined(stop)){
				$interval.cancel(stop);
				stop = undefined;
				$scope.running = false;
			}
		};
		
		function getData(data){
			data.then(function(data) {
				lifeGame.cellGrid = data;
			});
		}
		

	
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
		var clear = function() {
			return $http.get('http://localhost:8080/clear').then(function(response) {
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
		var remove = function() {
			$http.post('http://localhost:8080/remove');
		};
		
		return {
			getCellGrid : getCellGrid,
			next : next,
			setCell : setCell,
			add : add,
			remove: remove,
			clear: clear
		};
	} ]);