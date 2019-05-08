function copyLink(_link)
{
    Android.copyLink(_link);
}

function goToTask()
{
    Android.goToTask();
}

function c(id, num)
{
    document.getElementsByName(id)[num].click();
}

function checkForm(cnt)
{
    var isTrueAns = [];
    for(var i = 0; i < cnt; i++)
    {
        isTrueAns.push(0);
        document.getElementById("question" + i).style.color = 'black';
        var q = document.getElementsByClassName("que" + i);
        for(var j = 0; j < q.length; j++)
        {
            q[j].style.fontSize = '16px';
            q[j].style.fontWeight = 400;
        }
    }
    document.getElementById("testError").style.display = 'none';
    document.getElementById("testResult").style.display = 'none';
    var right = 0;
    for(var i = 0; i < cnt; i++)
    {
        var q = document.getElementsByName(i);
        var x = -1;
        for(var j = 0; j < q.length; j++)
        {
            if(q[j].checked)
            {
                x = q[j].value;
                break;
            }
        }
        if(x == -1)
        {
            document.getElementById("testError").style.display = 'block';
            return false;
        }
        if(x == 1)
        {
            right++;
            isTrueAns[i] = 1;
        }
    }
    document.getElementById("testResult").style.display = 'block';
    document.getElementById("testResult").innerHTML = "Ваш результат " + right + " из " + cnt + ".";
    if(10 * right >= 8 * cnt)
    {
        document.getElementById("testResult").style.color = 'green';
        document.getElementById("testResult").innerHTML += " Отлично справились!";
    }
    else if(10 * right >= 4 * cnt)
    {
        document.getElementById("testResult").style.color = '#081bfe';
        document.getElementById("testResult").innerHTML += " Поработайте над ошибками.";
    }
    else
    {
        document.getElementById("testResult").style.color = 'red';
        document.getElementById("testResult").innerHTML += " Прочтите статью еще раз.";
    }
    document.getElementById("testResult").innerHTML += "<br>";
    for(var i = 0; i < cnt; i++)
    {
        if(isTrueAns[i])
        {
            document.getElementById("question" + i).style.color = 'green';
        }
        else
        {
            document.getElementById("question" + i).style.color = 'red';
        }
        var q = document.getElementsByClassName("que" + i);
        for(var j = 0; j < q.length; j++)
        {
            if(q[j].children[0].value == 1)
            {
                q[j].style.fontSize = '18px';
                q[j].style.fontWeight = 800;
            }
        }
    }
}