$(document).ready(function($) {       
      
    var wbrMatchDivsByRound = createBracket($('#wbr'), wbrInfo, alignWbrRound, 'right', null);
    createBracket($('#lbr'), lbrInfo, alignLbrRound, 'left', wbrMatchDivsByRound);
    createBracket($('#fin1'), fin1Info, alignFinalRound, 'right', wbrMatchDivsByRound);
    if (fin2Info.rounds[0].games[0].gameState != 'NotStartedYet') {
        createBracket($('#fin2'), fin2Info, alignFinalRound, 'right', wbrMatchDivsByRound);
    }                    
    setContainerDivsWidth('#pgpTourney');
    setBlinkingForOngoing();
});
    
function createBracket(base, matchInfo, alignRound, leftOrRight, referenceBracket) {
    var matchDivsByRound = [];
    for (var roundIndex=0; roundIndex<matchInfo.rounds.length; roundIndex++) {    
        var round = matchInfo.rounds[roundIndex];
                         
        var bracketClass = (leftOrRight == 'left' ? 'leftBracket' : 'rightBracket'); 
        var bracket = checkedAppend('<div class="bracket ' + bracketClass + '"></div>', base);
        checkedAppend('<div class="roundName">' + round.name + '</div>', bracket);
                            
        var matchDivs = [];
        matchDivsByRound.push(matchDivs);
        
        //setup the match boxes round by round
        for (var i=0; i<round.games.length; i++) {                     
            var vOffset = checkedAppend('<div></div>', bracket);
        
            var match = round.games[i];
            var matchHtml = buildMatchHtml(match);
            var matchDiv = checkedAppend(matchHtml, bracket);
            matchDivs.push(matchDiv);
                                
            alignRound(roundIndex, i, matchDivsByRound, vOffset, bracket, referenceBracket);
        } // end for: games          
    } // end for: roundIndex
      
    addWinnerBox(base, matchInfo, matchDivsByRound[matchInfo.rounds.length - 1][0]);
                    
    return matchDivsByRound;
}

function alignWbrRound(roundIndex, gameIndex, matchDivsByRound, vOffset, currentBracket, referenceBracket) {
    var matchDiv = matchDivsByRound[roundIndex][gameIndex];
                    
    if (roundIndex > 0) {
        tweakOffset(vOffset, matchDivsByRound[roundIndex-1][gameIndex*2], matchDiv);
        tweakMatchDivSpacer(matchDivsByRound[roundIndex-1][gameIndex*2+1], matchDiv);
                        
    } else {
        checkedAppend('<div class="small-spacer"></div>', currentBracket);
    }
}
                
function alignLbrRound(roundIndex, gameIndex, matchDivsByRound, vOffset, currentBracket, wbrBracket) {
    var matchDiv = matchDivsByRound[roundIndex][gameIndex];
                    
    if (roundIndex  < 2) {
        // rounds 1 and 2 should be aligned with WBR
        tweakOffset(vOffset, wbrBracket[0][gameIndex*2], matchDiv);
        tweakMatchDivSpacer(wbrBracket[0][gameIndex*2+1], matchDiv);
                        
    } else {
        // alignment for following rounds changes every 2 rounds
        var tweakRoundIndex = 0;
        if (roundIndex % 2 == 0) {
            tweakRoundIndex = roundIndex - 1;
        }
        else {
            tweakRoundIndex = roundIndex - 2
        };
                                    
        tweakOffset(vOffset, matchDivsByRound[tweakRoundIndex][gameIndex*2], matchDiv);
        tweakMatchDivSpacer(matchDivsByRound[tweakRoundIndex][gameIndex*2+1], matchDiv);
    }
   
}
                
function alignFinalRound(roundIndex, gameIndex, matchDivsByRound, vOffset, currentBracket, wbrBracket) {
    tweakOffset(vOffset, wbrBracket[0][(wbrBracket[0]).length/2-1], matchDivsByRound[roundIndex][gameIndex]);
}
                
function tweakOffset(vOffset, alignTo, matchDiv) {
    //offset to line up tops
    var desiredOffset = alignTo.position().top - matchDiv.position().top;
    //offset by half the previous match-height
    desiredOffset += alignTo.height() / 2;
    vOffset.height(desiredOffset);
}
                
function tweakMatchDivSpacer(stretchTo, matchDiv) {
    //tweak our size so we stretch to the middle of the appropriate element
    var newH = stretchTo.position().top + stretchTo.height()/2 - matchDiv.position().top;
    var deltaH = newH - matchDiv.height();
    matchDiv.height(newH);
    var spacer = matchDiv.find('.spacer');
    spacer.height(spacer.height() + deltaH);
}
                
function addWinnerBox(base, matchInfo, finalMatchDiv){
    //setup the final winners box; just a space for a name whose bottom is centrally aligned with the last match
    bracket = checkedAppend('<div class="bracket rightBracket"></div>', base);
    var vOffset = checkedAppend('<div></div>', bracket);
    var html = '<div class="winner">' + fmtName(getTeamName(matchInfo.winner)) + '</div>';
    var winnerDiv = checkedAppend(html, bracket);
    vOffset.height(finalMatchDiv.position().top - winnerDiv.position().top + finalMatchDiv.height() / 2 - winnerDiv.height());
}
                
function buildMatchHtml(match) {
    var matchDivId = 'match' + buildMatchId(match.id);
    var matchDivClass='match';
    if (match.gameState == 'Ongoing') {
        matchDivClass= 'match ongoing';
    }
          
    var matchHtml = '<div class="' + matchDivClass +'" id="' + matchDivId + '">'
    + '<div class="p1">' + fmtName(getTeamName(match.teamHome)) + '</div>'
    + '<div class="spacer"></div>'
    + '<div class="p2">' + fmtName(getTeamName(match.teamAway)) + '</div>';
                        
    return matchHtml;
}

function buildMatchId(id) {
    return id.prefix + '-' + id.round +  '-' + id.match;
}
    
function getTeamName(team) {
    if (team != null) {
        return team.name;
    } else {
        return ' ';
    }
}
    
function fmtName(name) {
    return null != name ? name : '?';
}
    
function checkedAppend(rawHtml, appendTo) {
    var html = $(rawHtml);
    if (0 == html.length) {
        throw "Built ourselves bad html : " + rawHtml;
    }
    html.appendTo(appendTo);      
    return html;
}
                    
function setContainerDivsWidth(container) {
    var sum=0;
    $(container + ' div.tournament').each( function(){
        sum += $(this).outerWidth(true);
    });
    $(container).width( sum );
}
                    
function setBlinkingForOngoing(){
    // blink ongoing games
    var blink =  function() {
        effectBlink('ongoing')
    }

    var interval = setInterval(blink, 1000);
}
    
function effectBlink(classname) {    
    $("."+classname).fadeTo('slow', 0.5).fadeTo('slow', 1.0)
}