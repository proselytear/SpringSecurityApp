	$(document).ready(function() {
		var table = $('#objectview').DataTable();

		$('#objectview tbody').on('click', 'tr', function() {
			var data = table.row(this).data();

		});
	});