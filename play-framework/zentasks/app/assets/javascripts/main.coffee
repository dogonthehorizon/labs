$('.options dt, .users dt').on 'click', (e) ->
  e.preventDefault()
  if $(e.target).parent().hasClass('opened')
    $(e.target).parent().removeClass('opened')
  else
    $(e.target).parent().addClass('opened')
    $(document).one 'click', ->
      $(e.target).parent().removeClass('opened')
  false

$.fn.editInPlace = (method, options...) ->
  this.each ->
    methods =
      #public methods
      init: (options) ->
        valid = (e) =>
          newValue = @input.val()
          options.onChange.call(options.context, newValue)
        cancel = (e) =>
          @el.show()
          @input.hide()
        @el = $(this).dblclick(methods.edit)
        @input = $('<input type="text" />')
          .insertBefore(@el)
          .keyup (e) ->
            switch(e.keyCode)
              #Enter key
              when 13 then $(this).blur()
              when 27 then cancel(e)
          .blur(valid)
          .hide()
      edit: ->
        @input
          .val(@el.text())
          .show()
          .focus()
          .select()
        @el.hide()
      close: -> (newName) ->
        @el.text(newName).show()
        @input.hide()
    if(methods[method])
      return methods[method].apply(this, options)
    else if (typeof method == 'object')
      return methods.init.call(this, method)
    else
      $.error('Method ' + method + 'does not exist.')

class Drawer extends Backbone.View
  initialize: ->
    $(@el).children('li').each (i,group) ->
      new Group
        el: $(group)
      $('li', group).each (i,project) ->
        new Project
          el: $(project)
    $('#newGroup').click @addGroup
  addGroup: ->
    r = jsRoutes.controllers.Projects.addGroup()
    $.ajax
      url: r.url
      type: r.type
      context: this
      success: (data) ->
        _view = new Group
          el: $(data).appendTo('#projects')
        $(_view.el).find('.groupName').editInPlace('edit')

class Group extends Backbone.View
  events:
    'click  .toggle':     'toggle'
    'click  .newProject': 'newProject'
  newProject: (e) ->
    @el.removeClass('closed')
    r = jsRoutes.controllers.Projects.add()
    $.ajax
      url: r.url
      type: r.type
      context: this
      data:
        group: @el.attr('data-group')
      success: (tpl) ->
        _list = $('ul',@el)
        _view = new Project
          el: $(tpl).appendTo(_list)
        $_view.el.find('.name').editInPlace('edit')
      error: (err) ->
        $.error('Error: ' + err)
  toggle: (e) ->
    e.preventDefault()
    @el.toggleClass('closed')
    false


class Project extends Backbone.View
  events:
    'click  .delete': 'deleteProject'
  initialize: ->
    @id = $(@el).attr('data-project')
    @name = $('.name', @el).editInPlace
      context: this
      onChange: @renameProject
  renameProject: (name) ->
    @loading(true)
    r = jsRoutes.controllers.Projects.rename(@id)
    $.ajax
      url: r.url
      type: r.type
      context: this
      data:
        name: name
      success: (data) ->
        @loading(false)
        @name.editInPlace('close', data)
      error: (err) ->
        @loading(false)
        $.error('Error ' + err)
  loading: (display) ->
    if (display)
      @el.children('.delete').hide()
      @el.children('.loader').show()
    else
      @el.children('.delete').show()
      @el.children('.loader').hide()
  deleteProject: (e) ->
    e.preventDefault()
    @loading(true)
    r - jsRoutes.controllers.Project.delete(@id)
    $.ajax
      url: r.url
      type: r.type
      context: this
      success: ->
        @el.remove()
        @loading(false)
      error: (err) ->
        @loading(false)
        $.error('Error ' + err)
    false

$ ->
  drawer = new Drawer el: $('#projects')