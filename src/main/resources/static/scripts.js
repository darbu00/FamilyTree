// script.js
// document.addEventListener("DOMContentLoaded", function () {
//   fetchData();
// });
//
//
function toggleTable() {
  var sender = event.target.id;
  var table = document.getElementById(sender);
  if (table.style.display === "none") {
    table.style.display = "table";
  } else {
    table.style.display = "none";
  }
}

// <button onclick="toggleTable()">Show/Hide Table</button>

function retrieveElementId(event) {
  const elementId = event.target.id;
  return elementId;
}    


function fetchData() {
  // Assuming you have an API endpoint that returns JSON data
  fetch("https://example.com/api/data")
    .then((response) => response.json())
    .then((data) => {
      displayData(data);
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

function displayData(data) {
  const dataContainer = document.getElementById("data-container");

  // Clear existing data
  dataContainer.innerHTML = "";

  // Iterate over the data and create HTML elements to display it
  data.forEach((item) => {
    const dataItem = document.createElement("div");
    dataItem.classList.add("data-item");
    dataItem.textContent = `ID: ${item.id}, Name: ${item.name}, Age: ${item.age}`;
    dataContainer.appendChild(dataItem);
  });
}


    <script th:inline="javascript">

        var people = [];

/*<![CDATA[*/

people = /*[[${people}]]*/ 'default';

/*]]>*/

        //alert(people[0].firstName.trim());

        var autocList = [];
  for(var p in people){
    autocList.push({value: people[p].id, label: people[p].firstName.trim() + ((people[p].middleName.trim().length <= 1) ? '' : " " + people[p].middleName.trim()) + 
((people[p].lastName.trim().length <= 1) ? '' : " " + people[p].lastName.trim()) + 
((people[p].generation.trim().length <= 1) ? '' : " " + people[p].generation.trim()) + 
((people[p].marriedName.trim().length <= 1) ? '' : " " + people[p].marriedName.trim()) + 
((people[p].nickName.trim().length <= 1) ? '' : ", " + people[p].nickName.trim()), desc: "Id : " + people[p].id, icon: ""});
  }
        //alert(autocList[0].label);

 //  Autocomplete for parents names  
      $('.autoc').on("focus", function () {
        //$("#project")
            alert(autocList[0].label);

          $(this).autocomplete({
            minLength: 2,
            source: autocList,
            // focus: function (event, ui) {
            //   $(".autoc #parentSearch").val(ui.item.label);
            //   return false;
            // },
            // select: function (event, ui) {
            //   $("#project").val(ui.item.label);
            //   $("#project-id").val(ui.item.value);
            //   $("#project-description").html(ui.item.desc);
            //  // $("#project-icon").attr("src", "images/" + ui.item.icon);
            //
            //   return false;
            // },
          })
        //   .autocomplete("instance")._renderItem = function (ul, item) {
        //   return $("<li>")
        //     .append("<div>" + item.label + "<br>" + item.desc + "</div>")
        //     .appendTo(ul);
        // };
      });
    </script>

